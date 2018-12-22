<#include "/commons/_detailheader.ftl" />
<#assign currentBaseUrl="/paysettingwechat"/>
<#import "/commons/_macro_controller.ftl" as cont/>
<style>
    .dd-group .fluidbox .lab-item {
        margin-top: 3px;
    }

    .panel-fit body.panel-noscroll {
        overflow-y: scroll;
    }
</style>

<script language="javascript">
	$(function() {
		$("#add").click(function() {
			if ($('#addForm').form('validate')) {
                $('#addForm').ajaxSubmit(function(){
                    location.href = '${currentBaseUrl}';
                });
			}
		});
		
		$("#back").click(function() {
			location.href = '${currentBaseUrl}';
		});

	});

</script>

<div class="wrapper">
	<div class="formbox-a">
		<h2 class="h2-title">
			新增微信支付设置<span class="s-poar"> <a
				class="a-back" href="${currentBaseUrl}">返回</a>
			</span>
		</h2>

		<div class="form-contbox">
			<form method="post" class="validForm" id="addForm" name="addForm" 
				enctype="multipart/form-data" action="${currentBaseUrl}/doAdd"> 
			<dl class="dl-group">
				<dt class="dt-group">
					<span class="s-icon"></span>基本信息
				</dt>
				<dd class="dd-group">
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>订单名称: </label>
							<input type="text" id="orderName" name="orderName"
								class="txt w550 easyui-validatebox"
								missingMessage="订单名称必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                > 发送给微信的订单名称</font>
                        	</label>
                        </p>
					</div>
					<div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>开发者ID: </label>
							<input type="text" id="appid" name="appid" value="wx001da85510cce45a"
								class="txt w550 easyui-validatebox"
								missingMessage="开发者ID必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                >在公众号平台-基本配置-开发者ID(AppID)或微信支付申请成功后的邮件里</font>
                        	</label>
                        </p>
					</div>
                    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>商户号: </label>
							<input type="text" id="mchid" name="mchid" value="1503136041"
								class="txt w550 easyui-validatebox"
								missingMessage="商户号必填，2-20个字符"
								data-options="required:true,validType:'length[2,20]'" />
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                >在支付平台-产品中心-开发配置-商户信息或微信支付申请成功后的邮件里</font>
                        	</label>
                        </p>
					</div>
                    <div class="fluidbox">
						<p class="p12 p-item">
							<label class="lab-item"><font class="red">*</font>应用密钥: </label>
							<input type="text" id="appsecret" name="appsecret" value="badf48c05eb7dba63dbf758b6c46c7ef" readonly
								class="txt w550 easyui-validatebox"
								missingMessage="应用密钥必填"
								data-options="required:true" />
						</p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                >应用密钥，在公众号平台-基本配置-开发者密码(AppSecret)</font>
                        	</label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>API密钥: </label>
                            <input type="text" id="key" name="key" value="PpG8vfoA85C8RNPWbnWSqrkPuyzBhzUz" readonly
                                   class="txt w550 easyui-validatebox"
                                   missingMessage="API密钥必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                >PI密钥，在支付平台-账户中心-API安全-API密钥-设置API密钥，密码为自己设置，在网上使用工具或使用java的UUID生成32位随机字符后填入</font>
                        </label>
                        </p>
                    </div>
<!--                     <div class="fluidbox"> -->
<!--                         <p class="p12 p-item"> -->
<!--                             <label class="lab-item"><font class="red">*</font>通知地址: </label> -->
<!--                             <input type="text" id="notifyUrl" name="notifyUrl" readonly -->
<!--                                    class="txt w550 easyui-validatebox" -->
<!--                                    missingMessage="通知地址必填，2-20个字符" -->
<!--                                    data-options="required:true,validType:'length[2,20]'" /> -->
<!--                         </p> -->
<!--                         <p class="p12 p-item"> -->
<!--                             <label class="lab-item">&nbsp;</label> <label> <font -->
<!--                                 >异步回调地址</font> -->
<!--                         </label> -->
<!--                         </p> -->
<!--                     </div> -->
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>二维码请求地址: </label>
                            <input type="text" id="codeAddr" name="codeAddr" value="https://api.mch.weixin.qq.com/pay/unifiedorder"
                                   class="txt w550 easyui-validatebox" readonly
                                   missingMessage="二维码请求地址必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                >生成二维码数据的连接</font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>网页授权地址: </label>
                            <input type="text" id="oauth2Url" name="oauth2Url" value="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"
                                   class="txt w550 easyui-validatebox" readonly
                                   missingMessage="网页授权地址必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                ></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>网页授权access-token地址: </label>
                            <input type="text" id="oauth2Token" name="oauth2Token" value="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
                                   class="txt w550 easyui-validatebox" readonly
                                   missingMessage="获取网页授权access-token地址必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                ></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>微信内部创建订单地址: </label>
                            <input type="text" id="createOrderUrl" name="createOrderUrl" value="https://api.mch.weixin.qq.com/pay/unifiedorder"
                                   class="txt w550 easyui-validatebox" readonly
                                   missingMessage="微信内部创建订单地址必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                ></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>微信访问token: </label>
                            <input type="text" id="accessToken" name="accessToken" value="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                                   class="txt w550 easyui-validatebox" readonly
                                   missingMessage="微信访问token必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                ></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>公众号获取用户信息: </label>
                            <input type="text" id="userInfo" name="userInfo" 
                            		value="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID"
                                   class="txt w550 easyui-validatebox" readonly
                                   missingMessage="公众号获取用户信息必填"
                                   data-options="required:true" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                ></font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red">*</font>应用授权作用域: </label>
                            <@cont.select class="txt w550 easyui-validatebox" id="appScope" codeDiv="WXPAY_SCOPE_BASE" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label><label> 
                            <font >如果需要调用JSAPI，则需要从公众号获取信息，获取用户信息有以下两种：</font><br>
                            <font >snsapi_base：不弹出授权页面，直接跳转，只能获取用户openid</font><br>
                            <font >snsapi_userinfo：弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息</font>
                        </label>
                        </p>
                    </div>
<!--                     <div class="fluidbox"> -->
<!--                         <p class="p12 p-item"> -->
<!--                             <label class="lab-item"><font class="red">*</font>自定义状态值: </label> -->
<!--                             <input type="text" id="customerState" name="customerState" -->
<!--                                    class="txt w550 easyui-validatebox" -->
<!--                                    missingMessage="自定义状态值必填，2-40个字符" -->
<!--                                    data-options="required:true,validType:'length[2,40]'" /> -->
<!--                         </p> -->
<!--                         <p class="p12 p-item"> -->
<!--                             <label class="lab-item">&nbsp;</label> <label> <font -->
<!--                                 > </font> -->
<!--                         </label> -->
<!--                         </p> -->
<!--                     </div> -->
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>状态: </label>
                            <@cont.select class="txt w550 easyui-validatebox" name="status" id="status" codeDiv="PAY_STATUS" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                >机构下此项支付设置只能有一个是可用状态 </font>
                        </label>
                        </p>
                    </div>
                    <div class="fluidbox">
                        <p class="p12 p-item">
                            <label class="lab-item"><font class="red"></font>是否启用通知: </label>
                            <@cont.select class="txt w550 easyui-validatebox" name="enableNotify" id="enableNotify" codeDiv="ENABLE_NOTIFY" />
                        </p>
                        <p class="p12 p-item">
                            <label class="lab-item">&nbsp;</label> <label> <font
                                >启用通知后，系统在支付完成会向业务系统异步发送支付成功通知 </font>
                        </label>
                        </p>
                    </div>
						
				</dd>
			</dl>
			<p class="p-item p-btn">
				<input type="button" id="add" class="easyui-linkbutton" value="提交" /> <input
					type="button" id="back" class="easyui-linkbutton" value="返回" />
			</p>
			</form>
		</div>
	</div>
</div>

<#include "/commons/_detailfooter.ftl" />
