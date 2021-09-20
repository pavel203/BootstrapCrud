function getAllUsers() {
    $.ajax({
        url: "/getAllUsers",
        type: "GET",
        dataType: "json",
    }).done((msg) => {
        let allUsers = JSON.parse(JSON.stringify(msg));
        $("#allUsersTable tbody").empty();
        $.each(allUsers, (i, user) => {

            const roleNames = user.roles.map(function(item) {
                return item['authority'];
            });

            $("#allUsersTable tbody").append(
                $("<tr>").append(
                    $("<td>").text(user.id),
                    $("<td>").text(user.firstName),
                    $("<td>").text(user.lastName),
                    $("<td>").text(user.age),
                    $("<td>").text(user.email),
                    $("<td>").text(roleNames),
                    $("<td>").append(`<div data-whatever="${user.id}" data-toggle="modal" class="btn btn-info" data-target="#editModal">Edit</div>`),
                    $("<td>").append(`<div data-whatever="${user.id}" data-toggle="modal" class="btn btn-danger" data-target="#deleteModal">Delete</div>`)
                )
            );
        });
    });
}