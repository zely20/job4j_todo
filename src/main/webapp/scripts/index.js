function validation() {
        let desc = $('#desc').val();
        if (desc === '') {
            alert("Please write your Task");
            return false;
        } else {
            return true
        }
    }

function addTask() {
    if (validation()) {
        ($.ajax({
            type: "POST",
            url: "./addtask",
            data: {description: $('#desc').val(), idcat: $('#cIds').val().join(",")},
            dataType: "json",
        }));
        location.reload()
    }
}

function showAll() {
    let url;
    let items = "";
    if($('#all_task').is(':checked')){
        url = "./showAll";
    } else {
        url = "./showCurrentTask";
    }
    $.ajax({
        type: "GET",
        url: url,
        success: function (data) {
            $.each(data, function (index, element) {
                let id = element["id"];
                let description = element["description"];
                let create = element["created"];
                let done = element["isDone"];
                let author = element["user"].name;
                items += "<tr>"
                    + "<td>" + id + "</td>"
                    + "<td>" + description + "</td>"
                    + "<td>" + create + "</td>"
                    + "<td>" + done + "</td>"
                if (done) {
                    items += "<td>" + "<input class='form-check-input' type='checkbox' value='" + id + "' id='is_ready' onchange='markReady(this)'>" + "</td>"
                        + "<td>" + author + "</td>"
                        + "</tr>"
            } else
            {
                items += "<td>" + "<input class='form-check-input' type='checkbox' disabled checked id='is_ready' onchange='markReady(this)'>" + "</td>"
                    + "<td>" + author + "</td>"
                    + "</tr>"
            }
            });
            $('#items').html(items);
        }
    })
}

function markReady(button) {
    $(button).prop('disabled', 'disabled');
    ($.ajax({
        type: "POST",
        url: "./markReady",
        data: {id: $(button).val()},
        dataType: "json",
    }));
}

function getUserName(){
    ($.ajax({
        type: "GET",
        url: "./getusername",
        success: function (data) {
            $('#name_user').html('User ' + data.name);
        }
    }));
}

function getCategories() {
    let result;
    ($.ajax({
        type: "GET",
        url: "./getcategories",
        success: function (data) {
            $.each(data, function (index, element){
                let id = element["id"];
                let name = element["name"];
                result += "<option value=" + id + ">" + name + "</option>";
            })
            $('#cIds').html(result);
         }
        }
    ))
}
