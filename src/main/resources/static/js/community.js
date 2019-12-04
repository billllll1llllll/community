function post() {
    var questionId = $("#question_id").val()
    var questionContent = $("#question_content").val()

    if (!questionContent) {
        alert("不能回复空内容")
        return
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": questionContent,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                // $("#comment_section").hide()
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccept = confirm(response.message)
                    if (isAccept) {

                    } else {
                        window.open("https://github.com/login/oauth/authorize?client_id=be820bf954481a9efcd7&redirect_uri=http://localhost/callback&scope=user&state=1")
                        window.localStorage.setItem("cloasable", true)
                    }
                } else {
                    alert(response.message)
                }
            }
            console.log(response)
        },
        dataType: "json"
    })


}