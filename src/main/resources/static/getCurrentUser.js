function getCurrentUser() {
    $.ajax({

        url: "/getCurrentUser",
        type: "GET",
        dataType: "json",

    }).done((msg) => {

        let user = JSON.parse(JSON.stringify(msg));

        const roleNames = user.roles.map(function (item) {
            return item['authority'];
        });


        $("#current-user-table tbody").append(
            $("<tr>").append(
                $("<td>").text(user.id),
                $("<td>").text(user.firstName),
                $("<td>").text(user.lastName),
                $("<td>").text(user.age),
                $("<td>").text(user.email),
                $("<td>").text(roleNames))
        );

        if (!roleNames.toString().includes("ROLE_ADMIN")){
            $('#v-pills-user-tab').click();
        }
    })
}