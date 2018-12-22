<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/admin"/>
	<header class="am-topbar admin-header">
		<div class="g-top">
			<div class="g-head">
				<div class="title" id="topTitle">
					支付中心后台管理系统
				</div>
				<div class="m-loguser">
					<li>
						<span>超级管理员</span>
						<a class="name">${(user.userName)!'请登录'}</a>
						<input id="userid" type="hidden" value="${(user.id)!}" />
						<a class="detail"></a>
						<a class="f-clear"></a>
						<ul>
							<li onclick="logout();">注销登录</li>
						</ul>
					</li>
				</div>
			</div>
		</div>
	</header>
	<div class="am-cf admin-main">
		<div class="nav">
			<div class="nav-top">
				<div id="mini">
					<img
						src="${(domainUrlUtil.PSC_PAYMENT_URL)!}/resources/img/navicon/fold.png">
				</div>
			</div>
	
			<ul id="accordionMenu">
				<li class="nav-item on"><a href="javascript:;"><i
						class="my-icon nav-icon icon_1"></i><span>订单管理</span><i
						class="my-icon nav-more"></i></a>
					<ul style="display: none;">
						<li><a href="javascript:;"
							onclick="addTab('订单管理','/payorder')"><span>订单管理</span></a></li>
						<li><a href="javascript:;"
							onclick="addTab('MisPOS订单管理','/payposorder')"><span>MisPOS订单管理</span></a></li>
						<li><a href="javascript:;"
							onclick="addTab('订单日志','/payorderlog')"><span>订单日志</span></a></li>
					</ul></li>
	
				<li class="nav-item"><a href="javascript:;"><i
						class="my-icon nav-icon icon_1"></i><span>支付设置</span><i
						class="my-icon nav-more"></i></a>
					<ul style="display: none;">
						<li><a href="javascript:;"
							onclick="addTab('支付策略','/paystrategy')"><span>支付策略</span></a></li>
						<li><a href="javascript:;"
							onclick="addTab('支付宝支付设置','/paysettingalipay')"><span>支付宝支付设置</span></a></li>
						<li><a href="javascript:;"
							onclick="addTab('微信支付设置','/paysettingwechat')"><span>微信支付设置</span></a></li>
						<li><a href="javascript:;"
							onclick="addTab('聚合支付设置','/paysettingintegration')"><span>聚合支付设置</span></a></li>
					</ul></li>
	
				<li class="nav-item"><a href="javascript:;"><i
						class="my-icon nav-icon icon_1"></i><span>交易管理</span><i
						class="my-icon nav-more"></i></a>
					<ul style="display: none;">
						<li><a href="javascript:;"
							onclick="addTab('支付通知','/paynotifylog')"><span>支付通知</span></a></li>
						<li><a href="javascript:;" onclick="addTab('交易记录','/paylog')"><span>交易记录</span></a></li>
					</ul></li>
	
				<li class="nav-item"><a href="javascript:;""><i
						class="my-icon nav-icon icon_1"></i><span>系统管理</span><i
						class="my-icon nav-more"></i></a>
					<ul style="display: none;">
						<li><a href="javascript:;"
							onclick="addTab('数据字典','/syscodemaster')"><span>数据字典</span></a></li>
					</ul></li>
	
				<li class="nav-item"><a href="javascript:;"
					onclick="addTab('机构管理','/sysorganization')"><i
						class="my-icon nav-icon icon_1"></i><span>机构管理</span><i
						class="my-icon nav-more"></i></a></li>
	
				<li class="nav-item"><a href="javascript:;"><i
						class="my-icon nav-icon icon_1"></i><span>支付统计</span><i
						class="my-icon nav-more"></i></a>
					<ul style="display: none;">
						<li><a href="javascript:;"
							onclick="addTab('支付类型收支占比','/orderpaymodelcount')"><span>支付类型收支占比</span></a></li>
						<li><a href="javascript:;"
							onclick="addTab('支付类型收支趋势','/orderpaymodeltrendbyday')"><span>支付类型收支趋势</span></a></li>
                        <li><a href="javascript:;"
                               onclick="addTab('按日支付类型收支趋势','/orderpaymodeltrendbyhour')"><span>按日支付类型收支趋势</span></a></li>
                        <li><a href="javascript:;"
                               onclick="addTab('订单成交量统计','/ordercompletedcount')"><span>订单成交量统计</span></a></li>
                        <li><a href="javascript:;"
                               onclick="addTab('今日支付流量统计','/todayorderpaystatecount')"><span>今日支付流量统计</span></a></li>
                        <li><a href="javascript:;"
                               onclick="addTab('月订单退款率','/monthorderrefundrate')"><span>月订单退款率</span></a></li>
					</ul></li>
			</ul>
		</div>
		<div class="admin-content">
			<div class="admin">
				<div id="tab" class="easyui-tabs"
					data-options="fit:true,border:false"></div>
			</div>
		</div>
	</div>
	
	<div id="rcmenu" class="easyui-menu">
		<div id="refresh">刷新</div>
		<div id="refreshAll">刷新全部</div>
		<div class="menu-sep"></div>
		<div id="closecur">关闭</div>
		<div id="closeall">关闭全部</div>
		<div id="closeother">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="closeright">关闭右侧标签页</div>
		<div id="closeleft">关闭左侧标签页</div>
	</div>
	<script type="text/javascript">
		function addTab(name, url) {
			var t = $('#tab');
			if (t.tabs('exists', name)) {
				t.tabs('select', name);
			} else {
				var content = '<iframe scrolling="auto" woziji="meiyou" frameborder="0" src="'
						+ url + '" style="width:100%; height:100%"></iframe>';
				var tabhtml_ = "t.tabs(\'add\',{" + "	title: name,"
						+ "	selected: true,";

				if (name != "首页") {
					tabhtml_ += "	closable: true,";
				}

				tabhtml_ += "	content: content" + "});";
				eval(tabhtml_);
			}
		}

		function logout() {
			location.href = '${currentBaseUrl}/exit';
		}

		$(function() {
			addTab('订单管理', '/payorder')

			// nav收缩展开
			$('#accordionMenu')
					.on(
							'click',
							'li.nav-item>a',
							function() {
								if (!$('.nav').hasClass('nav-mini')) {
									$(this).parent('li').addClass("on")
											.siblings('li').removeClass("on");
									$('.nav-item').children('ul').slideUp(300);
									if ($(this).next().css('display') == "none") {
										//展开未展开
										$('.nav-item').children('ul').slideUp(
												300);
										$(this).next('ul').slideDown(300);
										$(this).parent('li').addClass(
												'nav-show').siblings('li')
												.removeClass('nav-show');
									} else {
										//收缩已展开
										$(this).next('ul').slideUp(300);
										$('.nav-item.nav-show').removeClass(
												'nav-show');
									}
								}
							});
			//nav-mini切换
			$('.nav-top').on('click', function() {
				if (!$('.nav').hasClass('nav-mini')) {
					$('.nav-item.nav-show').removeClass('nav-show');
					$('.nav-item').children('ul').removeAttr('style');
					$('.nav').addClass('nav-mini');
					$('.admin').css("left", '80px');
				} else {
					$('.nav').removeClass('nav-mini');
					$('.admin').css("left", '210px');
				}
			});

			$(".m-loguser li:first").click(function() {
				$(this).find("ul").css("display", "block");
			});

			$(".m-loguser li:first ul").mouseleave(function() {
				$(this).hide();
			});

			//选中的默认变色
			$(".sideMenu li a").click(
					function() {
						$(this).css("color", "rgb(67, 193, 251)").closest("ul")
								.find("a").not(this).css("color", "#0e90d2");
					});

			$(".ra_div a").click(function() {
				$(this).addClass('s_hover');
				$(this).siblings().removeClass('s_hover');
			});

			$('#tab')
					.tabs(
							{
								tools : [
										{
											text : '刷新',
											iconCls : 'icon-reload',
											handler : function() {
												var currTab = $('#tab').tabs(
														'getSelected');
												var url = $(
														currTab
																.panel('options').content)
														.attr('src');
												var content = '<iframe scrolling="auto" frameborder="0" src="'
														+ url
														+ '" style="width:100%; height:100%"></iframe>';
												$('#tab').tabs('update', {
													tab : currTab,
													options : {
														content : content
													}
												});
											}
										},
										{
											text : '关闭',
											iconCls : 'icon-cross',
											handler : function() {
												var tab = $('#tab').tabs(
														'getSelected');
												var index = $('#tab').tabs(
														'getTabIndex', tab);
												$('#tab').tabs('close', index);
											}
										} ]
							});

			$(".tabs-header").bind('contextmenu', function(e) {
				e.preventDefault();
				$('#rcmenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			});
			$("#closecur").bind("click", function() {
				var tab = $('#tab').tabs('getSelected');
				var index = $('#tab').tabs('getTabIndex', tab);
				$('#tab').tabs('close', index);
			});
			$("#closeall").bind("click", function() {
				var tablist = $('#tab').tabs('tabs');
				for (var i = tablist.length - 1; i >= 0; i--) {
					$('#tab').tabs('close', i);
				}
			});
			$("#closeother").bind("click", function() {
				var tablist = $('#tab').tabs('tabs');
				var tab = $('#tab').tabs('getSelected');
				var index = $('#tab').tabs('getTabIndex', tab);
				for (var i = tablist.length - 1; i > index; i--) {
					$('#tab').tabs('close', i);
				}
				var num = index - 1;
				for (var i = num; i >= 0; i--) {
					$('#tab').tabs('close', 0);
				}
			});
			$("#closeright").bind("click", function() {
				var tablist = $('#tab').tabs('tabs');
				var tab = $('#tab').tabs('getSelected');
				var index = $('#tab').tabs('getTabIndex', tab);
				for (var i = tablist.length - 1; i > index; i--) {
					$('#tab').tabs('close', i);
				}
			});
			$("#closeleft").bind("click", function() {
				var tab = $('#tab').tabs('getSelected');
				var index = $('#tab').tabs('getTabIndex', tab);
				var num = index - 1;
				for (var i = 0; i <= num; i++) {
					$('#tab').tabs('close', 0);
				}
			});

			$("#refresh")
					.click(
							function() {
								var currTab = $('#tab').tabs('getSelected');
								var url = $(currTab.panel('options').content)
										.attr('src');
								var content = '<iframe scrolling="auto" frameborder="0" src="'
										+ url
										+ '" style="width:100%; height:100%"></iframe>';
								$('#tab').tabs('update', {
									tab : currTab,
									options : {
										content : content
									}
								});
							});

			$("#refreshAll")
					.click(
							function() {
								var tablist = $('#tab').tabs('tabs');
								for (var i = tablist.length - 1; i >= 0; i--) {
									var currTab = tablist[i];
									var url = $(
											currTab.panel('options').content)
											.attr('src');
									var content = '<iframe scrolling="auto" frameborder="0" src="'
											+ url
											+ '" style="width:100%; height:100%"></iframe>';
									$('#tab').tabs('update', {
										tab : currTab,
										options : {
											content : content
										}
									});
								}
							});

		});
	</script>
<#include "/commons/_detailfooter.ftl" />

