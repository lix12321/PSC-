var ctobj = $.ajax({ url: "static/js/ct.json", async: false });
var ctrols = JSON.parse(ctobj.responseText); 

(function($) {
    $.fn.controls = function(_3f2, _3f3) {
        if (typeof _3f2 == "string") {
            return $.fn.controls.methods[_3f2](this, _3f3);
        }
    };

    $.fn.controls.methods = {
        enabletodrag:function(jq) {
            $(jq).draggable({
                proxy: 'clone',
                revert: true,
                cursor: 'auto',
                onStartDrag: function(e) {
                    $(this).draggable('options').cursor = 'not-allowed';
                },
                onStopDrag: function(e) {
                    $(this).draggable('options').cursor = 'auto';
                }
            });
        },
        init:function(jq,o){
            
            if (ctrols.length>0) {
                $.each(ctrols,function(i,c){
                    var cells = c.cells;
                    var ct = "";
                    if (cells.length>0) {
                        $.each(cells,function(j,cts){
                            var name = cts.name;
                            ct += "<div class=\"drag icon-"+name+"\" type=\""+c.name+"\">"+name+"</div>";
                        });
                    }
                    var panel = {
                        title:c.cname,
                        content: ct,
                        selected: false
                    };
                    $(jq).accordion('add',panel);
                });

            }
            $.fn.controls.methods["enabletodrag"](".drag");
        }
    };

    $(function(){
        $("#controls").controls("init");
    });
})(jQuery);


function remevent() {
    var newValue = $("#inputor").val();
    var node = $("#script_tree").tree("getSelected");
    if (node && !node.children) {
        // var structid = node.id.split("_")[0];
        var structid = node.oid;
        var snode;
        if (node.text == "load"||node.text=="Function") {
            snode = $("#webstructure").tree("find", node.id.split("_")[0]);
        } else {
           snode = $("#webstructure").tree("find", structid); 
        }
        
        var events = snode.data.event;
        if (events.length>0) {
            var ff = true;
            $.each(events,function(i,value){
                for (var o in value) {
                    if (value[o]==node.text) {
                        ff = false;
                        value.fparams = node.params;
                        value.fcontent = newValue;
                    }
                }
            });
            if (ff) {
                events.push({
                    fname:node.text,
                    fparams:node.params,
                    fcontent:newValue
                });
            }
        }
        else {
            events.push({
                fname:node.text,
                fparams:node.params,
                fcontent:newValue
            });
        }
        parsejsontoxml();
    } 
}

function saveitems1() {
    var $grid = $("#tablecolumns");
    var selected = $grid.edatagrid("getSelected");
    var index = $grid.edatagrid("getRowIndex",selected);
    $grid.edatagrid("endEdit",index);


    $("#addtablecolumn").dialog("close");
    loadtreebydesiger1();
    var data = $("#webstructure").tree("getRoots");
    loadwebdisigner(data);
    $("body").getpoolxml("getcontrolbytree");
}

function closeastdialog1() {
    $("#addtablecolumn").dialog("close");
}

function addRow1() {
    $("#tablecolumns").edatagrid('addRow');
}

function deleteRow1() {
    var row = $("#tablecolumns").edatagrid("getSelected");
    var index = $("#tablecolumns").edatagrid("getRowIndex", row);
    $("#tablecolumns").edatagrid("deleteRow", index);
}

var loadeditdesinger1 = function(node) { //加载编辑条件输入
    function geteditdata(node) {
        var formdata = node;
        var childs = formdata.children;
        var json = [];
        $.each(childs, function(i, value) {
            var header, field;
            $.each(value.data.property, function(i, value) {
                if (value.name == "header") {
                    header = value.content;
                }
                if (value.name == "field") {
                    field = value.content;
                }
            });
            var j = {
                "name": header,
                "sign": field
            };
            json.push(j);
        });
        return json;
    }


    $("#tablecolumns").edatagrid({
        singleSelect: true,
        toolbar: '#tablecolumns-tb',
        height: 339,
        data: geteditdata(node),
        onDblClickRow: function(rowIndex, rowData) {
            var $asdgrid = $("#tablecolumns");
            $asdgrid.edatagrid("editRow", rowIndex);
        },
        onClickRow: function(rowIndex, rowData) {
            var $asdgrid = $("#tablecolumns");
            $asdgrid.edatagrid("endEdit", rowIndex);
        },
        onLoadSuccess: function() {
            // $(this).datagrid('enableDnd');
        }
    });
}

var loadeditdesinger2 = function(node) { //编辑tab标签内容
    function geteditdata(node) {
        var formdata = node;
        var json = [];
        if (formdata.children) {
            var childs = formdata.children;
            $.each(childs, function(i, value) {
                var name, url;
                $.each(value.data.property, function(i, value) {
                    if (value.name == "title") {
                        name = value.content;
                    } else if (value.name == "href") {
                        url = value.content;
                    }
                });
                var j = {
                    "tn": name,
                    "href": url
                };
                json.push(j);
            });
        }
        return json;
    }


    $("#tabgrid").edatagrid({
        singleSelect: true,
        toolbar: '#tabgrid-tb',
        height: 339,
        data: geteditdata(node),
        onDblClickRow: function(rowIndex, rowData) {
            var $asdgrid = $("#tabgrid");
            $asdgrid.edatagrid("editRow", rowIndex);
        },
        onClickRow: function(rowIndex, rowData) {
            var $asdgrid = $("#tabgrid");
            $asdgrid.edatagrid("endEdit", rowIndex);
        },
        onLoadSuccess: function() {
            // $(this).datagrid('enableDnd');
        }
    });
}

var loadtreebydesiger1 = function() { //根据添加内容加载树
    var treenode = $("#Swebstructure").tree("getSelected");
    var id = treenode.id;
    var parent = $("#Swebstructure").tree("getParent", treenode.target);

    var datas = $("#tablecolumns").edatagrid("getData").rows;
    var cn = $("#columnnum").textbox("getValue");

    // 删除初次的搜索条件
    var n1 = $("#webstructure").tree("find", id);
    $("#webstructure").tree("remove", n1.target);

    var n2 = $("#Swebstructure").tree("find", id);
    $("#Swebstructure").tree("remove", n2.target);
    // 添加编辑后的搜索条件


    var h = Math.ceil(datas.length / cn);
    var array = [],
        array1 = [];

    $.each(datas, function(i, value) {
        var type = "datagrid.item";
        var node = {
            id: value.sign,
            text: value.sign + "(" + type + ")",
            iconCls:"icon-"+type.replace(/\./g, "-"),
            data: {
                attr: {
                    type: type
                },
                property: [{
                    content: value.sign,
                    name:"id"
                }, {
                    content: value.sign,
                    name: "field"
                }, {
                    content: value.name,
                    name: "header"
                }],
                event: {}
            }
        }

        array.push(node);
    });

    n1.children = array;

    var selected1 = $("#webstructure").tree("find", parent.id);

    $("#webstructure").tree('append', {
        parent: selected1.target,
        data: n1
    });

    $("body").getpoolxml("loadSwebstructure");

}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function savexml() {
    remevent();
    var xml = $("#source_code").textbox("getValue");
    var param = getQueryString("param");
    $.ajax({
        url: 'html/saveXml',
        async: false,
        data: {
            "param": param,
            "xml": xml
        },
        type: 'post',
        dataType: 'json',
        success: function(data) {
            var msg;
            if (data.success) {
                msg = "保存成功！";
            } else {
                msg = "保存异常！";
            }
            $.messager.show({
                title: '提示',
                msg: msg,
                timeout: 2000,
                showType: 'slide',
                modal: true,
                style: {
                    right: ($(window).width() - 250) / 2,
                    top: ($(window).height() - 100) / 2
                }
            });
        }
    });
}

function getQueryString(name) { 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
    var r = window.location.search.substr(1).match(reg); 
    if (r != null) return unescape(r[2]); return null; 
}

function preview() {
    var param = JSON.parse(getQueryString("param"));
    var filePath = param.filePath;
    var a = filePath.split("\\");
    var f = false,na="";
    $.each(a,function(i,c){
        if (f) {
            na += c.split(".")[0];
            if (i!==a.length-1) {
                na += "/";
            }
        }
        if (c=="page") {
            f = true;
        }
    });
    var o = location.origin;
    var p = "/pt/page?path="
    window.open(o+p+na);
}

$.extend({
    mergecell: function(id,direction,$f) {
        var $tree = $("#webstructure");
        var node = $tree.tree("find",id);
        var rf=false,cf=false,property=[];
        var r=0,co=0,ro=0,col=0;
        $.each($f,function(i,c){
            if (i>0) {
                r += c.rowSpan;
                co += c.colSpan;
            }
            ro += c.rowSpan;
            col += c.colSpan;
        });

        if (node.data.property) {
            property = node.data.property;

            if (property.length>0) {

                $.each(property,function(i,c){
                    if (c.name=="rowspan") {
                        rf = true;
                        if (direction=="row") {
                            c.content = parseInt(c.content) + r;
                        }
                    } else if (c.name=="colspan") {
                        cf = true;
                        if (direction=="col") {
                            c.content = parseInt(c.content) + co;
                        }
                    }
                });
            }
        }
        if (direction=="row"&&!rf) {
            property.push({
                name:"rowspan",
                content:ro
            });

        } else if (direction=="col"&&!cf) {
            property.push({
                name:"colspan",
                content:col
            });
        }

        // 删除多余节点
        var $tree = $("#webstructure");
        
        if (direction=="col") {
            var rowa = $.findnextcolbynum($f);
            $.each(rowa,function(i,c){
                var node = $tree.tree("find",c);
                $tree.tree("remove",node.target);
            });
        } else if (direction=="row") {
            var cola = $.findnextcolbynum($f);
            $.each(cola,function(i,c){
                var node = $tree.tree("find",c);
                $tree.tree("remove",node.target);
            });
        }
    },
    findnextcolbynum: function($f) {
        var a = [];
        $.each($f,function(i,c){
            if (i>0) {
                var id = c.id;
                a.push(id);
            }
        });
        return a;
    },
    getctbyc: function(type) { //根据控件名称获取控件类型
        var ff = false;
        $.each(ctrols,function(i,c){
            var cells = c.cells;
            var name = c.name;
            if (cells&&cells.length>0) {
                $.each(cells,function(j,n){
                    if (n.name == type) {
                        ff = name;
                    }
                });
            }
        });
        return ff;
    }
});

function desingercontextmenu() {


    $(".u-cell,.u-td").bind('contextmenu',function(e){
        e.stopPropagation();
        e.preventDefault();

        var id = $(e.currentTarget).attr("id");
        var n = $("#Swebstructure").tree("find", id);
        if (n) {
            $("#Swebstructure").tree('select', n.target);
        } else {
            var node = $("#webstructure").tree("find", id);
            if (!node) {
                return;
            }
            $("#webstructure").tree('select', node.target);
        }

        if ($(e.currentTarget).hasClass("u-td")) {
            $('#detailmenu1').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        } else if ($(e.currentTarget).hasClass("u-cell")) {
            $('#detailmenu2').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        }
    });
}

var enabletodrop = function(target) { //拖拽事件

    function pullelememtbytype(type,tt) {
        var nnode,params;
        if (ctrols&&ctrols.length>0) {
            $.each(ctrols,function(i,c){
                if (c.name == tt) {
                    var cells = c.cells;
                    if (cells&&cells.length>0) {
                        $.each(cells,function(j,n){
                            if (n.name==type) {//判断是否存在特定结构
                                params = n.params;
                            }
                        });
                    }

                }
            });
        }

        if (!params) {//若无特定结构，则使用默认结构
            params = {struct:[{
                type: type,
                seq: 0
            }],number:{}};
            params.number[type] = 1;
        }
        nnode = $.getnodesbyparm(params)[0];
      
        return nnode;
    }

    function abletodrop(cnode,innode) {
        var t1,t2;
        t1 = getbigtypebynode(cnode);
        t2 = getbigtypebynode(innode);
        if (!t1||!t2) {
            return false;
        }
        if (t1=="bodyview"&&t2=="CellLayout") {
            return true;
        } else {
            return ablecontaincontroller(t1,t2);
        }
    }

    function abletodrop1(cnode,type) {
        var t1;
        t1 = getbigtypebynode(cnode);
        if (!t1) {
            return false;
        }
        if (t1=="bodyview"&&type=="CellLayout") {
            return true;
        } else {
            return ablecontaincontroller(t1,type);
        }
    }

    function getctbyc(type) {
        var ff = false;
        $.each(ctrols,function(i,c){
            var cells = c.cells;
            var name = c.name;
            if (cells&&cells.length>0) {
                $.each(cells,function(j,n){
                    if (n.name == type) {
                        ff = name;
                    }
                });
            }
        });
        return ff;
    }

    function showwarn() {
        // $.messager.show({
        //     title: '提示',
        //     msg: "该区域不能放置该拖拽控件",
        //     timeout: 1000,
        //     showType: 'slide',
        //     modal: true,
        //     style: {
        //         right: ($(window).width() - 250) / 2,
        //         top: ($(window).height() - 100) / 2
        //     }
        // });
    }

    function getruletodrop(drop,drap) {
        var ff = false;
        if (drop=="layoutControls") {
            if (drap=="layoutControls") {
                ff = true;
            } else if (drap=="standardControls") {
                ff = true;
            } else if (drap=="inputControls") {
                ff = false;
            }
        } else if (drop=="standardControls") {
            if (drap=="layoutControls") {
                ff = false;
            } else if (drap=="standardControls") {
                ff = false;
            } else if (drap=="inputControls") {
                ff = false;
            }
        } else if (drop=="inputControls") {
            if (drap=="layoutControls") {
                ff = false;
            } else if (drap=="standardControls") {
                ff = false;
            } else if (drap=="inputControls") {
                ff = false;
            }
        }
        return ff;
    }

    function ablecontaincontroller(type1,type2) {
        var rs = false;
        if (type1 == "Form"&&getctbyc(type2)=="inputControls") {
            rs = "true";
        }
        else if (type1 == "toolbar"&&getctbyc(type2)=="inputControls") {
            rs = "true";
        }
        else if (type1 == "Form"&&type2=="html") {
            rs = "true";
        }
        else if (type1 == "toolbar"&&type2=="html") {
            rs = "true";
        }
        else if (type1 == "panel"&&type2=="html") {
            rs = "true";
        }
        else if (getctbyc(type1) == "layoutControls"&&type2=="html") {
            rs = "true";
        }
        else if (getctbyc(type1)&&getctbyc(type2)) {
            var drop = getctbyc(type1);
            var drag = getctbyc(type2);
            if (getruletodrop(drop,drag)) {
                rs = true;
            }
        }
        else {
            rs = false;
        }
        return rs;
    }

    function getbigtypebynode(node) {
        var bigtype;
        if (node) {
            var type = node.data.attr.type;
            var $tree = $("#webstructure");
            if (type=="layout.grid.row") {
                return false;
            }
            else if (type=="layout.grid.column") {
                var pnode = $tree.tree("getParent",node.target);
                var ppnode = $tree.tree("getParent",pnode.target);
                bigtype = ppnode.data.attr.type;
                if (bigtype=="layout.grid"){
                    bigtype = "CellLayout";
                } else if (bigtype=="form") {
                    bigtype = "Form";
                }
            } else if (type=="layout.grid.row"||type=="datagrid.item") {
                var pnode = $tree.tree("getParent",node.target);
                bigtype = pnode.data.attr.type;
            } else if (type=="toolbar") {
                bigtype = false;
            } else {
                bigtype = type;
            }
        }
        return bigtype;
    }

    $(target).droppable({
        accept: '.drag,.u-cell',
        onDragEnter: function(e, source) {
            // console.log(e, source);
            // $(".over").removeClass('over');
            e.stopPropagation();
            $(source).draggable('options').cursor='auto';
            var parentid = $(this).attr("id");
            var pnode = $("#webstructure").tree('find', parentid);
            if ($(source).hasClass("f-select")) {
                //移动控件
                $(".dp").css("display","block");
                var id = $(source).attr("id");
                var cnode = $("#webstructure").tree('find', id);
                if(abletodrop(pnode,cnode)){

                    var sid = source.id;
                    var $ot = $("#webstructure");
                    var sn = $ot.tree("find",sid);
                    //判断拖放的位置是否是当前位置，如果是则不作操作
                    if (sn) {
                        var psn = $ot.tree("getParent",sn.target);
                        var nid = psn.id;
                        if (parentid!==nid) {
                            $(this).addClass('over');
                        } else {
                            $(".dp").css("display","none");
                        }
                    }

                }
            } else {
                //拖拽入控件
                var cname = $(source).text();
                if(abletodrop1(pnode,cname)){
                    $(this).addClass('over');
                }
            }
        },
        onDragLeave: function(e, source) {

            e.preventDefault();
            $(source).draggable('options').cursor = 'not-allowed';
            $(this).removeClass('over');
        },
        onDrop: function(e, source) {
            e.preventDefault();
            var parentid = $(".over").attr("id");
            if(!parentid) {
                return false;
            }
            var sid = source.id;
            var $ot = $("#webstructure");
            var sn = $ot.tree("find",sid);
            
            
            var pnode = $("#webstructure").tree('find', parentid);

            if ($(source).hasClass("u-cell")) {
                //移动控件
                var node = {
                    id: sn.id,
                    text: sn.text,
                    data: sn.data
                };
                $("#webstructure").tree('remove',sn.target);

                $("#webstructure").tree('append',{
                    parent:pnode.target,
                    data:[node]
                });

            }
            else {
                //拖拽入控件
                var cname = $(source).text();
                var type = $(source).attr("type");

                var nnode = pullelememtbytype(cname,type);
                $("#webstructure").tree('append',{
                    parent:pnode.target,
                    data:[nnode]
                });
            }

            var data = $("#webstructure").tree("getRoots");
            loadwebdisigner(data);
            parsejsontoxml();
            $("body").getpoolxml("loadSwebstructure");
        }
    });
}






