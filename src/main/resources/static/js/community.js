/**
 * �ύ�ظ�
 */
function post() {
    var questionId = $("#question_id").val()
    var questionContent = $("#question_content").val()
    console.log("questionId = " + questionId + " , questionContent = " + questionContent)
    comment2target(questionId, 1, questionContent)

}


function comment(e) {
    var id = e.getAttribute("data-id")
    var content = $("#input-" + id).val();
    console.log("id = " + id + " , content = " + content)
    comment2target(id, 2, content)
}


/**
 * չ����������
 */
function collapseComments(e) {

    var id = e.getAttribute("data-id")
    var comments = $("#comment-" + id);
    if (comments.hasClass("in")) {
        comments.removeClass("in")
    } else {
        comments.addClass("in")
    }
}

function comment2target(targetId, type, content) {

    if (!content) {
        alert("不能为空")
        return
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
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
