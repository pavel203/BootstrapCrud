$('document').ready(function() {

    $(() => {

        getAllUsers();
        getCurrentUser();

        $('#admin-profile').on('show.bs.tab', () => {
            getAllUsers();
        })
    })


    //***************New User Form Active*****************//
    $('#newUser').on('click', function (event) {

        event.preventDefault();

    });

    $("#submitNewUser").on('click', (event) => {
        event.preventDefault();

        let newUser = {
            firstName: $("#newFirstName").val(),
            lastName: $("#newLastName").val(),
            age: $("#newAge").val(),
            email: $("#newEmail").val(),
            password: $("#newPassword").val(),
            roles: $("#newRoles").val(),
        }

        $.ajax({
            url: "/newUser",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(newUser),
            statusCode: {
                200: function () {
                    $('#allUsers').click();
                }
            }
        })

    });

    //************Edit Modal Active***************************//
    $('#editModal').on('show.bs.modal', (event) => {

        let userID = $(event.relatedTarget).data('whatever');

        $.ajax({
            url: "/getOne/" + userID,
            type: "GET",
            dataType: "json",
        }).done((msg) => {

            let user = JSON.parse(JSON.stringify(msg));

            $('#idEdit').val(user.id);
            $('#firstNameEdit').val(user.firstName);
            $('#lastNameEdit').val(user.lastName);
            $('#ageEdit').val(user.age);
            $('#emailEdit').val(user.email);
            $('#passwordEdit').val(user.password);
            $('#selectRoles').empty();

        });

        let allRoles = $.ajax({
            url: '/getAllRoles',
            type: "GET",
            dataType: "json",
        }).done((msg) => {

            allRoles = msg;

            const roleNames = allRoles.map(function (item) {
                return item['authority'];
            });

            const select = document.getElementById('selectRoles');

            for (let i = 0; i < roleNames.length; i++) {
                const opt = roleNames[i];
                const el = document.createElement("option");
                el.textContent = opt;
                el.value = opt;
                select.appendChild(el);
            }
        });
    });

    $('#confirmEdit').on('click', function (event) {

        event.preventDefault();

        let editedUser = {
            id: $('#idEdit').val(),
            firstName: $('#firstNameEdit').val(),
            lastName: $('#lastNameEdit').val(),
            age: $('#ageEdit').val(),
            email: $('#emailEdit').val(),
            password: $('#passwordEdit').val(),
            roles: $('#selectRoles').val()
        }
        $.ajax({
            url: "/update",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(editedUser),
            statusCode: {
                200: function () {
                    $("#editModal").modal('hide');
                    getAllUsers();
                }
            }
        })

    });

    //*****************Delete Modal Active*************************//
    $('#deleteModal').on('show.bs.modal',function (event) {

        let userID = $(event.relatedTarget).data('whatever');

        $.ajax({

            url: "/getOne/" + userID,
            type: "GET",
            dataType: "json",

        }).done((msg) => {

            let user = JSON.parse(JSON.stringify(msg));

            $('#idDelete').val(user.id);
            $('#firstNameDelete').val(user.firstName);
            $('#lastNameDelete').val(user.lastName);
            $('#ageDelete').val(user.age);
            $('#emailDelete').val(user.email);
            $('#select').empty();

            const roleNames = user.roles.map(function (item) {
                return item['authority'];
            });

            const select = document.getElementById('select');

            for (let i = 0; i < roleNames.length; i++) {
                const opt = roleNames[i];
                const el = document.createElement("option");
                el.textContent = opt;
                el.value = opt;
                select.appendChild(el);
            }

            $("#delRef").on('click', function (event) {

                event.preventDefault()

                $.ajax({

                    url: "/delete/" + user.id,
                    type: "POST",
                    dataType: "text",

                }).done(() => {

                    $("#deleteModal").modal('hide');
                    getAllUsers();

                })
            });
        });
    });
});
