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
        }));
        location.reload()
    }
};

function showAll() {
    $.ajax({
        type: "GET",
        url: "./showAll",
        success: function (data) {
            $.each(data, function (index, element) {

                let id = element["id"];
                let description = element["description"];
                let create = element["created"];
                let done = element["isDone"];
                items += "<tr>"
                    + "<td>" + id + "</td>"
                    + "<td>" + description + "</td>"
                    + "<td>" + create + "</td>"
                    + "<td>" + done + "</td>"
                if (done) {
                    items += "<td>" + "<input class='form-check-input' type='checkbox' value='" + id + "' id='is_ready' onchange='markReady(this)'>" + "</td>"
                + "</tr>"
            } else
            {
                items += "<td>" + "<input class='form-check-input' type='checkbox' disabled checked id='is_ready' onchange='markReady(this)'>" + "</td>"
                + "</tr>"
            }
            });
            $('#items').html(items);
        }
    })
};

function markReady(button) {
    $(button).prop('disabled', 'disabled');
    ($.ajax({
        type: "POST",
        url: "./markReady",
        data: {id: $(button).val()},
        dataType: "json",
    }));
}