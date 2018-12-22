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
            编辑微信支付设置<span class="s-poar"> <a
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
                        <input type="hidden" name="id" value="${obj.id}">
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>订单名称: </label>
                                <input type="text" id="orderName" name="orderName"
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="订单名称必填，2-20个字符"
                                       data-options="required:true,validType:'length[2,20]'" value="${obj.orderName}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080"> 发送给微信的订单名称</font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>开发者ID: </label>
                                <input type="text" id="appid" name="appid"
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="开发者ID必填，2-20个字符"
                                       data-options="required:true,validType:'length[2,20]'" value="${obj.appid}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080">在公众号平台-基本配置-开发者ID(AppID)或微信支付申请成功后的邮件里</font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>商户号: </label>
                                <input type="text" id="mchid" name="mchid"
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="商户号必填，2-20个字符"
                                       data-options="required:true,validType:'length[2,20]'" value="${obj.mchid}" />
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080">在支付平台-产品中心-开发配置-商户信息或微信支付申请成功后的邮件里</font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>应用密钥: </label>
                                <input type="text" id="appsecret" name="appsecret" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="应用密钥必填"
                                       data-options="required:true" value="${obj.appsecret}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080">应用密钥，在公众号平台-基本配置-开发者密码(AppSecret)</font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>API密钥: </label>
                                <input type="text" id="key" name="key" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="API密钥必填"
                                       data-options="required:true" value="${obj.key}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080">PI密钥，在支付平台-账户中心-API安全-API密钥-设置API密钥，密码为自己设置，在网上使用工具或使用java的UUID生成32位随机字符后填入</font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>二维码请求地址: </label>
                                <input type="text" id="codeAddr" name="codeAddr" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="二维码请求地址必填"
                                       data-options="required:true" value="${obj.codeAddr}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080">生成二维码数据的连接</font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>网页授权地址: </label>
                                <input type="text" id="oauth2Url" name="oauth2Url" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="网页授权地址必填"
                                       data-options="required:true" value="${obj.oauth2Url}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080"></font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>网页授权access-token地址: </label>
                                <input type="text" id="oauth2Token" name="oauth2Token" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="获取网页授权access-token地址必填"
                                       data-options="required:true" value="${obj.oauth2Token}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080"></font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>微信内部创建订单地址: </label>
                                <input type="text" id="createOrderUrl" name="createOrderUrl" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="微信内部创建订单地址必填"
                                       data-options="required:true" value="${obj.createOrderUrl}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080"></font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>微信访问token: </label>
                                <input type="text" id="accessToken" name="accessToken" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="微信访问token必填"
                                       data-options="required:true" value="${obj.accessToken}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080"></font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>公众号获取用户信息: </label>
                                <input type="text" id="userInfo" name="userInfo" readonly
                                       class="txt w550 easyui-validatebox"
                                       missingMessage="公众号获取用户信息必填"
                                       data-options="required:true" value="${obj.userInfo}"/>
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080"></font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red">*</font>授权作用域: </label>
                                <@cont.select class="txt w550 easyui-validatebox" id="appScope" name="appScope" value="${obj.appScope}" codeDiv="WXPAY_SCOPE_BASE" />
                            </p>
                            <p class="p12 p-item">
	                            <label class="lab-item">&nbsp;</label>
		                          <label style="overflow: hidden;display: block;padding-left: 10px;"> 
		                            <font style="margin-left: 0px">如果需要调用JSAPI，则需要从公众号获取信息，获取用户信息有以下两种：<br>
		                            snsapi_base：不弹出授权页面，直接跳转，只能获取用户openid<br>
		                            snsapi_userinfo：弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息</font>
		                        </label>
	                        </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red"></font>状态: </label>
                                <@cont.select class="txt w550 easyui-validatebox" name="status" id="status" value="${obj.status}" codeDiv="PAY_STATUS" />
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080">机构下此项支付设置只能有一个是可用状态 </font>
                            </label>
                            </p>
                        </div>
                        <div class="fluidbox">
                            <p class="p12 p-item">
                                <label class="lab-item"><font class="red"></font>是否启用通知: </label>
                                <@cont.select class="txt w550 easyui-validatebox" name="enableNotify" id="enableNotify" value="${obj.enableNotify}" codeDiv="ENABLE_NOTIFY" />
                            </p>
                            <p class="p12 p-item">
                                <label class="lab-item">&nbsp;</label> <label> <font
                                    style="color: #808080">启用通知后，系统在支付完成会向业务系统异步发送支付成功通知 </font>
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
