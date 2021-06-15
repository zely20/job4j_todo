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
            async: true,
            data: {description: $('#desc').val()},
            dataType: "json",
        }))
    }
};
