function validation() {
    let desc = $('#desc').val();
    if(desc === '') {
        alert("Please write your Task");
        return false;
    } else {
        return true
    }
};

function addTask() {
    if (validation()) {
        ($.ajax({
            type: "POST",
            url: "./addtask",
            data: {description: $('#desc').val()},
            dataType: "json",
        }))
        location.reload()
    }
};
function showAll() {
    $.ajax({
        type: "GET",
        url: "./showAll",
        success: function (data) {
            $.each(data, function (index, element) {
                items += "<tr>"
                    + "<td>" + element["id"] + "</td>"
                    + "<td>" + element["description"] + "</td>"
                    + "<td>" + element["created"] + "</td>"
                    + "<td>" + element["isDone"] + "</td>"
                    + "</td>"
                    + "</tr>"
            });
            $('#items').html(items);
        }
    })
};
