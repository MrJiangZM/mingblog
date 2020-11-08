/**
 * Created with IntelliJ IDEA.
 * User: coder
 * Date: 15-4-21
 * Time: 上午11:11
 * To change this template use File | Settings | File Templates.
 * isSuccess = status >= 200 && status < 300 || status === 304;
 */
var Ajax = (function(){
    var ajax = {};
    jQuery.each(["get", "post", "delete", "put"], function (i, method) {
        ajax[method] = function (url, data, callback, failCallback, type) {
            //兼容data没有传，在第二个参数直接传了callback的情况
            if (jQuery.isFunction(data)) {
                type = type || callback;
                callback = data;
                data = undefined;
            }
            return jQuery.ajax({
                url: url,
                type: method,
                dataType: type,
                data: data,
                error: function (response) {
                    console.log(response);
                    failCallback();
                },
                success: function(d) {


                    var status = d["status"];
                    if (status == 200) {
                        return callback(d);
                    } else {
                        return failCallback();
                    }
                }
            });
        };
    });

    ajax.getJSON = function (url, data, callback, failCallback) {
        return ajax.get(url, data, callback, failCallback, "json");
    };
    return ajax;
})();
