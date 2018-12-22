<script>
// 取消遮罩
function unMask(obj) {
	obj.fadeOut('normal', function() {
		obj.remove();
	});
}

//easyui加载完毕后取消遮罩
var timeinterval_;
//在easyui组件渲染完毕时取消遮罩
$.parser.onComplete = function() {
	if (timeinterval_)
		clearTimeout(timeinterval_);
	timeinterval_ = setTimeout(unMask($("#loading")), 1000);
}
</script>

</body> 
</html>
