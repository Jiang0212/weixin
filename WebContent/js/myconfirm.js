/**
 * 重写了alert和confirm
 * example：
 * 	$.myAlert({
    content: "提示内容",
	confirm: function () {
	    确认回调方法
	}
}); 
 
$.myConfirm({
    content: '确认内容',
	confirm: function () {
        确定回调方法
    }
})
 */
$.myConfirm = function(args) {
			args = args || {};
			var title = args.title || "提示";
			var content = args.content || "";
			var confirmText = args.confirmText || "确认";
			var cancelText = args.cancelText || "取消";
			var confirm = args.confirm || function() {
			};
			var cancel = args.cancel || function() {
			};
			var id = $.randomString();
			var html = '';
			html += '<div class="modal fade" data-backdrop="static" id="' + id + '">';
			html += '<div class="modal-dialog modal-sm" role="document">';
			html += '<div class="modal-content" style="box-shadow: 0 1px 5px rgba(0,0,0,.25)">';
			html += '<div class="modal-header">';
			html += '<h6 class="modal-title">' + title + '</h6>';
			html += '</div>';
			html += '<div class="modal-body">' + content + '</div>';
			html += '<div class="modal-footer">';
			html += '<button type="button" class="btn btn-secondary alert-cancel-btn">'
					+ cancelText + '</button>';
			html += '<button type="button" class="btn btn-primary alert-confirm-btn">'
					+ confirmText + '</button>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			$("body").append(html);
			$("#" + id).modal("show");
			$(document).on("click", "#" + id + " .alert-confirm-btn",
					function() {
						$("#" + id).modal("hide");
						confirm();
					});
			$(document).on("click", "#" + id + " .alert-cancel-btn",
					function() {
						$("#" + id).modal("hide");
						cancel();
					});
		};
		$.myAlert = function(args) {
			args = args || {};
			var title = args.title || "提示";
			var content = args.content || "";
			var confirmText = args.confirmText || "确认";
			var confirm = args.confirm || function() {
			};
			var id = $.randomString();
			var html = '';
			html += '<div class="modal fade" data-backdrop="static" id="' + id + '">';
			html += '<div class="modal-dialog modal-sm" role="document">';
			html += '<div class="modal-content" style="box-shadow: 0 1px 5px rgba(0,0,0,.25)">';
			html += '<div class="modal-header">';
			html += '<h6 class="modal-title">' + title + '</h6>';
			html += '</div>';
			html += '<div class="modal-body">' + content + '</div>';
			html += '<div class="modal-footer">';
			html += '<button type="button" class="btn btn-primary alert-confirm-btn">'
					+ confirmText + '</button>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			$("body").append(html);
			$("#" + id).modal("show");
			$(document).on("click", "#" + id + " .alert-confirm-btn",
					function() {
						$("#" + id).modal("hide");
						confirm();
					});
		};
		$.randomString = function(len) {
			len = len || 32;
			var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
			/****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
			var maxPos = $chars.length;
			var pwd = '';
			for (i = 0; i < len; i++) {
				pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
			}
			return pwd;
		};