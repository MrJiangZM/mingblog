/**
 * 只传一个参数 ，标题不传就用默认标题，需要自定义标题就传入2个参数
 * @param text  内容
 * @param title 标题
 */
function showAlert(text, title) {
    if (title == null || title == '' || title == undefined) {
        title = '系统提示';
    }
    $("#alertTitle").html(title);
    $("#alertText").html(text);
    $("#showAlertID").modal();
}

/**
 * 传入只一个参数就是内容 如果传了2个参数那 第二个就是标题
 * 以此类推 第三个是取消的按钮显示的字 第四个是确认按钮显示的字
 * @param text  内容
 * @param title 标题
 * @param cancel    取消
 * @param ok    确认
 * @return 直接返回div的对象，可以连着点modal启用
 *
 * 栗子
 showConfirm('测试','你好','算了','滚蛋').modal({
     onConfirm: function() {
         //干点啥
     },
    onCancel: function() {
         //干点啥
    }
});
 */
function showConfirm(text, title, cancel, ok) {
    if (title == null || title == '' || title == undefined) {
        title = '系统提示';
    }
    if (cancel == null || cancel == '' || cancel == undefined) {
        cancel = '取消';
    }
    if (ok == null || ok == '' || ok == undefined) {
        ok = '确定';
    }
    $("#confirmTitle").html(title);
    $("#confirmText").html(text);
    $('#confirmCancel').text(cancel);
    $('#confirmOK').text(ok);
    return $('#showConfirmID');
}

function numberFormat(x){
    var f_x = parseFloat(x);
    if (isNaN(f_x))
    {
        return 0;
    }
    var f_x = Math.round(x*100)/100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0)
    {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2)
    {
        s_x += '0';
    }
    return s_x;
}

/**
 * 只有第一个参数 text 是必填项，其余都是可选项
 * 直接上栗子
 showPrompt('内容','标题','取消','确定').modal({
      onConfirm: function(e) {
        alert('你输入的是：' + e.data)
      }
    });
 */
function showPrompt(text, title, cancel, ok) {
    if (title == null || title == '' || title == undefined) {
        title = '系统提示';
    }
    if (cancel == null || cancel == '' || cancel == undefined) {
        cancel = '取消';
    }
    if (ok == null || ok == '' || ok == undefined) {
        ok = '确定';
    }
    $("#promptTitle").html(title);
    $("#promptText").html(text);
    $('#promptCancel').text(cancel);
    $('#promptOK').text(ok);
    return $('#showPromptID');
}

function showLoading(){
    $('#showLoading').modal('open');
}

function closeLoading(){
    $('#showLoading').modal('close');
}

function syntaxHighlight(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}

(function($) {
  'use strict';

  $(function() {
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
      $.AMUI.fullscreen.toggle();
    });

    try{
         $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
              $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
         });
    }catch(error){
        console.log(error);
    }

    $(document).on("click",".btn-submit",function () {
        showLoading();
        var remote = $(this).attr('formaction');
        var action = $(".ajax_form form").attr("action");
        $(".ajax_form form ").attr("action",remote);
        $(".ajax_form form").ajaxSubmit({
            success: function (d) {
                 var status = d["status"];
                 if (status == 200) {
                     closeLoading();
                 } else {
                     closeLoading();
                     showAlert("操作失败");
                 }
            },
            error:function(XmlHttpRequest, textStatus, errorThrown){
                closeLoading();
            }
        });
        $(".ajax_form form").attr("action",action);
        window.location.reload();
        return false;
    });

  });

})(jQuery);
