﻿var module = $.ajax({ url: "static/js/module1.json", async: false });
var modulejsona = JSON.parse(module.responseText); 

function addselect() {
    $("#addselectdialog").dialog("open");
}

function saveitems() {
//     var $grid = $("#asdgrid");
//     var selected = $grid.edatagrid("getSelected");
//     var index = $grid.edatagrid("getRowIndex",selected);
//     $grid.edatagrid("endEdit",index);

//     $("#addselectdialog").dialog("close");
//     loadtreebydesiger();
//     var data = $("#webstructure").tree("getRoots");
//     loadwebdisigner(data);
//     $("body").getpoolxml("getcontrolbytree");
}

function closeastdialog() {
    $("#addselectdialog").dialog("close");
}

function addRow() {
    $("#asdgrid").edatagrid('addRow');
}

function GenNonDuplicateID(randomLength){
  return Number(Math.random().toString().substr(3,randomLength) + Date.now()).toString(36)
}

function deleteRow() {
    var row = $("#asdgrid").edatagrid("getSelected");
    var index = $("#asdgrid").edatagrid("getRowIndex", row);
    $("#asdgrid").edatagrid("deleteRow", index);
}

// var loadeditdesinger = function() { //加载编辑条件输入
//     function geteditdata() {
//         var formdata = $("#Swebstructure").tree("getSelected");
//         var childs = formdata.children;
//         var json = [];
//         if (childs) {
//             $.each(childs,function(i,value){
//                 var name;
//                 var type =  value.data.attr.type;
//                 var key;
//                 if (type=="combobox"||type=="textbox"||type=="combotree"||type=="spinner"||type=="datetimebox") {
//                     key = "label";
//                 } else if (type=="button") {
//                     key = "text";
//                 }

//                 $.each(value.data.property,function(i,value){
//                     if (value.name==key) {
//                         name = value.content;
//                     }
//                 });

//                 var j = {
//                     "name": name,
//                     "name1": value.text,
//                     "type": value.data.attr.type,
//                     "sign": value.id
//                 };
//                 json.push(j);
//             });
//         }
//         return json;
//     }


//     $("#asdgrid").edatagrid({
//         singleSelect: true,
//         toolbar: '#asdgrid-tb',
//         data: geteditdata(),
//         height: 338,
//         onDblClickRow: function(rowIndex, rowData) {
//             var $asdgrid = $("#asdgrid");
//             $asdgrid.edatagrid("editRow", rowIndex);
//         },
//         onClickRow: function(rowIndex, rowData) {
//             var $asdgrid = $("#asdgrid");
//             $asdgrid.edatagrid("endEdit", rowIndex);
//         },
//         onLoadSuccess: function() {
//             $(this).datagrid('enableDnd');
//         }
//     });
// }

// var loadtreebydesiger = function() {//根据添加内容加载树
//     var datas = $("#asdgrid").edatagrid("getData").rows;
//     var cn = $("#columnnum").textbox("getValue");

//     var treenode = $("#Swebstructure").tree("getSelected");
//     var id = treenode.id;
//     var parent = $("#Swebstructure").tree("getParent",treenode.target);

//     // 删除初次的搜索条件
//     var n1 = $("#webstructure").tree("find",id);
//     $("#webstructure").tree("remove",n1.target);

//     var n2 = $("#Swebstructure").tree("find",id);
//     $("#Swebstructure").tree("remove",n2.target);
//     // 添加编辑后的搜索条件
    

//     var h = Math.ceil(datas.length/cn);
//     // console.log(n1,n2);
//     var array = [],array1 = [];

//     $.each(datas,function(i,value){
//         var type = value.type;
//         var node;
//         if (type=="combobox"||type=="textbox"||type=="combotree"||type=="spinner"||type=="datetimebox") {
//             node = {
//                 id:type+i,
//                 text:type+i+"("+value.type+")",
//                 data:{
//                     attr: {
//                         type:value.type
//                     },
//                     property: [{
//                         content:value.name,
//                         name:"label"
//                     }],
//                     event:{}
//                 },
//                 iconCls:"icon-"+type.replace(/\./g, "-")
//             }
//         }
//         else if (type=="button") {
//             node = {
//                 id:value.sign,
//                 text:value.name+"("+value.type+")",
//                 data:{
//                     attr: {
//                         type:value.type
//                     },
//                     property: [{
//                         content:value.name,
//                         name:"text"
//                     }],
//                     event:{}
//                 },
//                 iconCls:"icon-"+value.type.replace(/\./g, "-")
//             }
//         }
//         array.push(node);
//     });

//     var n_ = 1;

//     for (var i = 0; i < h; i++) {
//         var a = [];
//         var i_ = i+1;
//         for (var j = 0+i*cn; j < i_*cn; j++) {
//             var node = {};
//             if(array.length>j) {
//                 var type = array[j].data.attr.type;
//                 if (type=="combobox"||type=="textbox"||type=="combotree"||type=="spinner"||type=="datetimebox") {
//                     node = {
//                         id:array[j].id,
//                         text:array[j].text,
//                         iconCls:"icon-"+type.replace(/\./g, "-"),
//                         data:{
//                             attr: {
//                                 type:type
//                             },
//                             property: [{
//                                 content:array[j].data.property[0].content,
//                                 name:"label"
//                             }],
//                             event:{}
//                         }
//                     }
//                 }
//                 else if (type=="button") {
//                     node = {
//                         id:array[j].id,
//                         text:array[j].text,
//                         iconCls:"icon-"+type.replace(/\./g, "-"),
//                         data:{
//                             attr: {
//                                 type:type
//                             },
//                             property: [{
//                                 content:array[j].data.property[0].content,
//                                 name:"text"
//                             }],
//                             event:{}
//                         }
//                     }
//                 }
//                 a.push({
//                     id:"layout_cells"+n_,
//                     text:"layout_cells"+n_,
//                     iconCls:"icon-layout-grid-column",
//                     data:{
//                         attr:{
//                             type:"layout.grid.column"
//                         },
//                         property:[],
//                         event:{}
//                     },
//                     children:[node]
//                 });

                
//             }
//             else {
//                 a.push({
//                     id:"layout_cells"+n_,
//                     text:"layout_cells"+n_,
//                     iconCls:"icon-layout-grid-column",
//                     data:{
//                         attr:{
//                             type:"layout.grid.column"
//                         },
//                         property:[],
//                         event:{}
//                     }
//                 });
//             }

//             n_++;

//         }
//         var rownode = {
//             iconCls:"icon-layout-grid-row",
//             data: {
//                 attr: {
//                     type: "layout.grid.row"
//                 },
//                 property: [],
//                 event:{}
//             },
            
//             children:a
//         };
//         array1.push(rownode);
//     }


//     n2.children = array;

//     n1.children = array1;

//     var selected = $("#Swebstructure").tree("find",parent.id);

//     var selected1 = $("#webstructure").tree("find",parent.id);

    
    
//     $("#Swebstructure").tree('append',{
//         parent:selected.target,
//         data:n2
//     });

//     $("#webstructure").tree('append',{
//         parent:selected1.target,
//         data:n1
//     });

    

// }


var applyproperytotree = function(nodeid) {//将属性面板中的参数同步到树中

    function designrowandcolumn(p2,treenode) {
        var row,column;
        $.each(p2,function(i,value){
            if (value.name=="row") {
                row = value.content;
            } 
            else if (value.name=="column") {
                column = value.content;
            }
        });
        if (!row) {
            row = 1;
        }

        // 判断如果行列没有改变则不执行以下行列转换操作
        var id = treenode.id;
        var node = $("#webstructure").tree("find",id);
        var p = node.data.property;
        var ro,co;
        $.each(p,function(i,value){
            if (value.name=="row") {
                ro = value.content;
            } 
            else if (value.name=="column") {
                co = value.content;
            }
        });
        if (!ro) {
            ro = 1;
        }
        if (row==ro&&column==co) {
            return false;
        }

        var struct = [];
        for (var i = 0; i < row; i++) {
            var child = [];
            for (var j = 0; j < column; j++) {
                child.push({
                    type: "layout.grid.column",
                    seq: column*i+j
                });
            }

            var r = {
                type: "layout.grid.row",
                seq: i,
                child: child
            };

            struct.push(r);
        }

        var number = {
            "layout.grid.row":row,
            "layout.grid.column":row*column
        };

        var param = {
            struct:struct,
            number:number
        };
        
        var nnode = $.getnodesbyparm(param);
        var sform = $("#webstructure").tree("find",treenode.id);
        var cc = [];
        if (sform.children&&sform.children.length>0) {
            var sformdata = sform.children;
            $.each(sformdata,function(i,value){
                var childs =  value.children;
                if (childs) {
                $.each(childs,function(j,value1){
                    if (value1.children&&value1.children.length>0) {
                        var childss = value1.children;
                        cc.push(childss);
                    }
                });
                }
            });
        }

        var num = 0;
        $.each(nnode,function(i,value){
            var nchild = value.children;
            $.each(nchild,function(j,val){
                if (cc[num]) {
                    val.children = cc[num];
                    num++;
                }
            });
        });

        var pnode = $("#webstructure").tree("getChildren",treenode.target);

        $.each(pnode,function(i,value){
            $("#webstructure").tree("remove",value.target);
        });

        
        $("#webstructure").tree('append',{
            parent:treenode.target,
            data:nnode
        });

    }

    try {
        var $tree = $("#webstructure");
        var propertys = $("#property").propertygrid("getData").rows;
        var treenode = $tree.tree("find",nodeid);
        var p1 = treenode.data.property;
        var p2 = [];
        var nname;

        $.each(propertys,function(i,value){
            if (value.value=="") {
                return true;
            } else if (value.attrname=="id") {
                nname = value.value;
            }
            p2.push({
                name:value.attrname,
                content:value.value
            });
        });

        var id = treenode.id;
        var snode = $tree.tree("find",id);
        var type = snode.data.attr.type;
        
        snode.text = nname+"("+type+")";

        if (treenode.data.attr.type=="form"||treenode.data.attr.type=="layout.grid"||treenode.data.attr.type=="toolbar") {
            designrowandcolumn(p2,treenode);
        } else if (treenode.data.attr.type=="html") {
            var p = treenode.data.property;
            var cc;
            $.each(p,function(i,c){
                if (c.name == "content") {
                    cc = c.content;
                }
            });
            if (cc) {
                p2.push({
                    name:"content",
                    content:cc
                });
            }
        }

        snode.data.property = p2;
        $("body").getpoolxml("loadSwebstructure");
        var data = $("#webstructure").tree("getRoots");
        loadwebdisigner(data);
        return true;
    }catch(err) {
        console.error(err);
        return false;
    }

}

var loadpropertys = function(node) { //根据选中控件显示其属性 

    function findv(name,val,nodes) {
        var dv;
        if (nodes) {
            $.each(nodes,function(i,value){
                if (value.name == name) {
                    dv = value.content;
                }
            });
        }
        
        if (!dv) {
            dv = val;
        }
        return dv;
    }

    function cte(type) {
        var editor;
        if (typeof(type)=="object") {
            editor = type;
        } else {
            switch (type) {
                case "String":
                editor = "text";
                break;

                case "Boolean":
                editor = {
                    "type":"checkbox",
                    "options":{
                        "on":true,
                        "off":false
                    }
                };
                break;

                case "Number":
                editor = "numberbox";
                break;

                case "Date":
                editor = "datebox";
                break;

                default:
                editor = "text";
                break;
            }
        }
        return editor;
    }

    function loadevent(e,node1) { //格式化属性显示数据
        var s = [];
        var id = node1.id;
        if (node) {
            s = node.data.property;
        }
        var json = {};
        json["total"] = 3;
        var array = [];
        $.each(e, function(i, value) {

            var dv;

            dv = findv(value["name"],value["dv"],s);

            var option;
            if (value["option"]) {
                option = value["option"];
            }

            array.push({
                "name": value["desc"],
                "value": dv,
                "attrname": value["name"],
                "nodeid": id,
                "editor": cte(value["type"])
            });
        });
        json["rows"] = array;
        return json;
    }

    // console.log(node);
    var type = node.data.attr.type;
    var ta = type.split(".");
    var modulejson;
    $.each(modulejsona, function(i, value) {
        if (value.type == ta[0]) {
            if (ta.length == 2) {
                modulejson = {
                    "property":value[ta[1]],
                    "event":[]
                };
            }
            else {
                modulejson = value;
            }
        }
        else if (type == value.type){
            modulejson = value;
        }

    });

    var p,e;
    if (modulejson) {
        p = loadevent(modulejson.property,node);
    }
    else {
        var space = {
           "total": 0,
           "rows": []
        };
        p = space;
        e = space;
    }
    $("#property").propertygrid({
        data: p,
        onEndEdit: function(rowIndex, rowData, changes) {
            var _bool = applyproperytotree(rowData.nodeid);
            if(!_bool) return;
            parsejsontoxml();

            var data = $("#webstructure").tree("getRoots");
            loadwebdisigner(data);
            $("body").getpoolxml("loadSwebstructure");

            $("body").getpoolxml("getcontrolbytree");
        },
        onDblClickRow: function(rowIndex, rowData){
            if (rowData.attrname=="columns") {
                var c = rowData.value,data;
                try
                {
                    if (c&&c!=="undefined") {
                        c = c.substring(1,c.length-1).replace(/'/g,"\"");
                        data = JSON.parse(c);
                    } else {
                        data = [];
                    }
                    $("#columngrid").datagrid({
                        data:data
                    });
                    $("#editcolumns").dialog("open");
                }
                catch(err)
                {
                   console.log(err);
                }
                
            }
        }
    });
};

var time = null;

var bindclickontable = function() { //绑定设计页面选中事件及后续显示属性

    function selectnode(o,e) {
        var id = $(o).attr("id");
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
    }

    function enabledrag(o) {
        $(o).draggable({
            revert:true,
            cursor:'auto',
            proxy:'clone',
            onBeforeDrag:function(e){
            },
            onStartDrag:function(e){
                $(this).draggable('options').cursor='not-allowed';
                $(this).draggable('proxy').addClass('dp');
            },
            onDrag:function(e){
                // var $c = $(".dp").prev();
                // var $p1 = $c.parent();
                // var $p2 = $(".over");
                // var p1id = $p1.attr("id");
                // var p2id = $p2.attr("id");
                // if (p1id==p2id) {
                //     $(".dp").css("z-index","-1");
                // } else {
                //     $(".dp").css("z-index","0");
                // }
            }
        })
    }

    

    $(".f-select").click(function(e){
        e.stopPropagation();
        if (e.button == 0) {
            if (e.ctrlKey==1){
                $(this).addClass("u-focus");
            }
            else{
                $(".u-focus").removeClass("u-focus");
                $(this).addClass("u-focus");
            }
            selectnode(this,e);
        }
    });

    $(".u-cell").click(function(e){
        enabledrag(this);
    });

    $(".u-cell").dblclick(function(e){
        var id = this.id;
        var $st = $("#script_tree");
        var $tree = $("#webstructure");
        var ui = $st.tree("find","UI");
        var c = ui.children;
        if (c) {
            $.each(c,function(i,n1){
                $st.tree("collapse",n1.target);
            });
        }
        var $t = $("#moduletab");
        var on = $tree.tree("find",id);
        var p = on.data.property;
        var nid,n;
        $.each(p,function(i,ce){
            if (ce.name=="id") {
                nid = ce.content;
            }
        });
        if (nid) {
            n = $st.tree("find",nid);
            if(n) {
                $st.tree("expand",n.target);
                $st.tree("select",n.target);
                $t.tabs("select","脚本");
            }
        }
    });
}

$.extend({
    getnodesbyparm:function(parms){
        var numbers = parms.number;
        var struct = parms.struct;
        var ida = {};
        for(var o in numbers){
            var type = o.replace(/\./g, "_");
            var num = numbers[o];
            if (type == "layout_grid") {
                type = "layout";
            }
            else if (type == "layout_grid_column") {
                type = "layout_cell";
            }
            else if (type == "datagrid") {
                type = "grid";
            }
            else if (type == "datagrid_item") {
                type = "header";
            }
            var ids = $.getidbyexit(type,num);
            ida[type] = ids;
        }

        // console.log(ida);

        var nodes = $.setidstostruct(ida,struct);
        
        return nodes;
    },
    setidstostruct:function(ida,struct) {
        var nodes = [];
        $.each(struct,function(i,value){
            var type = value.type.replace(/\./g, "_");
            if (type == "layout_grid") {
                type = "layout";
            }
            else if (type == "layout_grid_column") {
                type = "layout_cell";
            }
            else if (type == "datagrid") {
                type = "grid";
            }
            else if (type == "datagrid_item") {
                type = "header";
            }
            var seq = value.seq;
            var ids = ida[type];
            var pnode = $.getnodebytype(value.type,ids[seq]);
            if (value.child) {
                var cnodes = $.setidstostruct(ida,value.child);
                pnode.children = cnodes
            }
            nodes.push(pnode);

        });
        return nodes;
    },
    getproperybytype:function(type,datas){//根据控件类型查找控件库中对应属性
        var property = [];
        if (modulejsona&&modulejsona.length>0) {
            $.each(modulejsona,function(i,value){
                for(var o in value) {
                    if (type == "datagrid.item") {
                        if (o=="type"&&value[o]=="datagrid") {
                            if (value.item&&value.item.length>0) {
                                var items = value.item;
                                $.each(items,function(j,val){

                                    if (datas&&datas[val.name]) {
                                        property.push({
                                            name: val.name,
                                            content: datas[val.name]
                                        });
                                    }
                                    else if (val.dv!=="") {
                                        property.push({
                                            name: val.name,
                                            content: val.dv
                                        });
                                    }
                                });
                            }
                        }
                    }
                    else if (o=="type"&&value[o]==type){

                        if (value.property&&value.property.length>0) {
                            var p = value.property;
                            $.each(p,function(j,val){

                                if (datas&&datas[val.name]) {
                                    property.push({
                                        name: val.name,
                                        content: datas[val.name]
                                    });
                                }
                                else if (val.dv!=="") {
                                    property.push({
                                        name: val.name,
                                        content: val.dv
                                    });
                                }
                            });
                        }
                    }
                    
                }

            });
        }
        return property;
    },
    getnodebytype:function(type,id) {
        var node;
        if (type=="button"||$.getctbyc(type)!=="inputControls") {
            switch (type) {
                case "layout.grid.row":
                    var property = $.getproperybytype(type);
                    property.unshift({
                        name:"id",
                        content:id
                    });
                    node = {
                        "id":id,
                        "text": id+"("+type+")",
                        "iconCls":"icon-"+type.replace(/\./g, "-"),
                        "data":{
                            attr:{
                                type:type
                            },
                            property:property,
                            event:[]
                        }
                    };
                    break;

                case "datagrid.item":
                    var datas = {
                        header: id,
                        field: id
                    }; 
                    var property = $.getproperybytype(type,datas);
                    property.unshift({
                        name:"id",
                        content:id
                    });
                    node = {
                        "id": id,
                        "text": id+"("+type+")",
                        "iconCls":"icon-"+type.replace(/\./g, "-"),
                        "data":{
                            attr:{
                                type:type
                            },
                            property:property,
                            event:[]
                        }
                    };
                    break;

                default:
                    var property = $.getproperybytype(type);
                    property.unshift({
                        name:"id",
                        content:id
                    });
                    node = {
                        "id": id,
                        "text": id+"("+type+")",
                        "iconCls":"icon-"+type.replace(/\./g, "-"),
                        "data":{
                            attr:{
                                type:type
                            },
                            property:property,
                            event:[]
                        }
                    };
                    break;
            }

        } else if ($.getctbyc(type)=="inputControls") {
            var datas = {
                label: id
            }; 
            var property = $.getproperybytype(type,datas);
            property.unshift({
                name:"id",
                content:id
            });
            if (type == "searchbox") {
                property.push({
                    name:"menu",
                    content:"#menu-"+id
                });
            }
            node = {
                "id": id,
                "text": id+"("+type+")",
                "iconCls":"icon-"+type.replace(/\./g, "-"),
                "data":{
                    attr:{
                        type:type
                    },
                    property:property,
                    event:[]
                }
            };
        }
        
        return node;
    },
   
    getidbyexit:function(type,num) {
        var n = 0;
        var ids = [];
        for (var i = 0; i < num; i++) {
            var n1 = $.getidfromn(type,n);
            ids.push(type+n1);
            n= n1+1;
        }  
        return ids;
    },
    getidfromn:function(type,n) {
        var id = type + n;
        var $tree = $("#webstructure");
        var node = $tree.tree("find",id);
        while (node) {
            n++;
            id = type + n;
            node = $tree.tree("find",id);
        }
        return n;
    },
    loadnoticesbyscripttree:function(){
        loadnoticesbyscripttree();
    },
    updateproperybynode: function(nid,properyty,value){
        var $t = $("#webstructure");
        var n = $t.tree("find",nid);
        value = value.replace(/"/g,"'");
        var f = true;
        if (n&&n.data.property&&n.data.property.length>0) {
            var p = n.data.property;
            $.each(p,function(i,c){
                if (c.name==properyty) {
                    c.content = value;
                    f = false;
                }
            });
        }

        if (f) {
            n.data.property.push({
                name:properyty,
                content:value
            });
        }

        parsejsontoxml();
        var data = $t.tree("getRoots");
        loadwebdisigner(data);
        $("body").getpoolxml("loadSwebstructure");
        $("body").getpoolxml("getcontrolbytree");

    }
});



(function($) {
    $.fn.getpoolxml = function(_3f2, _3f3) {
        if (typeof _3f2 == "string") {
            return $.fn.getpoolxml.methods[_3f2](this, _3f3);
        }
    };

    $.fn.getpoolxml.methods = {
        getnodes: function(jq,o) {
            var arr = [];
            $(o).children("view").each(function(i) {
                var node = {};
                var id = $(this).attr("id");
                var name = $(this).attr("name");
                var type = $(this).attr("type");

                if (type == "datagrid") {
                    var arr1 = $.fn.getpoolxml.methods["getnodes"](this, $(this).children("group"));
                    node.children = arr1;
                }

                else {
                    if ($(this).children("view").length > 0) {
                        var arr1 = $.fn.getpoolxml.methods["getnodes"](this, $(this));
                        node.children = arr1;
                    }
                }
                node.id = id;
                node.text = id+"("+type+")";
                node.iconCls = "icon-"+type.replace(/\./g, "-");
                var attr = {
                    "type": type
                };

                var $pro = $(this).children("property");
                var property = [];
                $.each($pro, function(i, value) {
                    var json = {};
                    if ($(value).attr("name")!=="colgroup") {
                        if ($(value).attr("name")) {
                            json.name = $(value).attr("name");
                        }
                        json.content = $(value).html();
                        property.push(json);
                    }
                });

                //判断属性中是否已经存在id，如若存在则改写值，如若不存在，则添加
                var ff = false;
                $.each(property,function(i,c){
                    if (c.name=="id") {
                        c.content = id;
                        ff = true;
                    }
                });

                if (!ff) {
                    property.unshift({
                        name:"id",
                        content:id
                    });
                }

                var $event = $(this).children("event");
                var event = [];
                $.each($event, function(i, value) {
                    var name = $(value).attr("name");
                    var s = $(value).children("script")[0].innerHTML;

                    var params = "";
                    if ($(value).children("parameters").length>0) {
                        params = $(value).children("parameters")[0].innerHTML;
                    }

                    var a = s.substring(9);
                    var b = a.substring(0,a.length-3);
                    // event[name] = b;
                    event.push({
                        fname:name,
                        fcontent:b,
                        fparams:params
                    });
                });

                var data = {
                    "attr": attr,
                    "property": property,
                    "event": event
                };
                node.data = data;
                arr.push(node);
            });
            return arr;
        },

        deletefromjson: function(jq,json) {
            var rjsons = [];
            $.each(json,function(i,value){
                var rjson = {
                    id:value.id,
                    text:value.text,
                    data:value.data,
                    iconCls:"icon-"+value.data.attr.type.replace(/\./g, "-")
                };
                if (value.children&&value.children.length>0) {
                    if ("layout.grid" == value.data.attr.type) {
                        var tchild = [];
                        var child = value.children;
                        $.each(child,function(j,ch){
                            var child2 = ch.children;
                            if (child2) {
                            $.each(child2,function(n,chh) {
                                var chhh;
                                if(chh.children) {
                                    chhh = $.fn.getpoolxml.methods["deletefromjson"](this, chh.children);
                                }
                                else {
                                    chhh = [];
                                }
                                tchild.push({
                                    id: chh.id,
                                    text: chh.text,
                                    data: chh.data,
                                    children: chhh,
                                    iconCls:"icon-"+chh.data.attr.type.replace(/\./g, "-")
                                });
                            });
                            }
                        });
                        rjson.children = tchild;
                    } 
                    else if ("form" == value.data.attr.type||"toolbar" == value.data.attr.type) {
                        var tchild = [];
                        var child = value.children;
                        $.each(child,function(j,ch){
                            var child2 = ch.children;
                            if (child2) {
                            $.each(child2,function(n,chh) {
                                var child3 = chh.children;
                                if(child3) {
                                    $.each(child3,function(k,chhh){
                                        tchild.push({
                                            id: chhh.id,
                                            text: chhh.text,
                                            data: chhh.data,
                                            iconCls:"icon-"+chhh.data.attr.type.replace(/\./g, "-")
                                        });
                                    });
                                }
                            });
                            }
                        });
                        rjson.children = tchild;
                    } 
                    else {
                        var child = value.children;
                        var chhh = $.fn.getpoolxml.methods["deletefromjson"](this, child);
                        rjson.children = chhh;
                    }
                } 
                rjsons.push(rjson);
            });
            return rjsons;
        },

        loadSwebstructure: function(jq) {
            var datas = $("#webstructure").tree("getRoots");
            var r1 = $.fn.getpoolxml.methods["deletefromjson"](this, datas);
            $("#Swebstructure").tree({
                data: r1
            });
        },

        inint: function() {
            var param = getQueryString("param");
            $.ajax({
                url: 'html/readXml',
                async: false,
                data: {
                    "param":param
                },
                type: 'get',
                dataType: 'xml',
                success: function(data) {
                    var ui = $(data).find("ui");
                    var rs = $.fn.getpoolxml.methods["getnodes"](this, ui);
                    var e = [];

                    var resource = $(ui).children("resource");
                    if($(resource).children("script")[0]) {
                        var js = $(resource).children("script")[0].innerHTML;
                        e.push({
                            fname:"Function",
                            fparams:"e",
                            fcontent:js.substring(9,js.length-3)
                        });
                    }
                    

                    var event = $(ui).children("event");
                    var name = $(event).attr("name");
                    if($(event).children("script")[0]) {
                        var script = $(event).children("script")[0].innerHTML;
                        e.push({
                            fname:name,
                            fparams:"e",
                            fcontent:script.substring(9,script.length-3)
                        });
                    }

                    rs = [{
                        id:"UI",
                        text:"UI",
                        iconCls:"icon-ui",
                        data:{
                            attr:{
                                type:"ui"
                            },
                            property:[],
                            event:e
                        },
                        children:rs
                    }];
                    $("#webstructure").tree({
                        data: rs
                    });

                    $.fn.getpoolxml.methods["loadSwebstructure"](this);
                }
            });
        },


        getcontrolbytree: function() {
            var datas = $("#webstructure").tree("getRoots");
            var treedata = getcontrollbyevents(datas);
            $("#inputor").val("");
            $("#f-head").html("");
            $("#f-foot").html("");
            $("#script_tree").tree({
                data:treedata,
                onSelect:function(node) {
                    if (node.iconCls=="icon-event"||node.iconCls=="icon-function") {
                        var controllid = node.id.split("_")[0];
                        var eventis = node.id.split("_")[1];
                        var ocontrollid = node.oid;
                        var js = "",orginnode;

                        if (node.text == "load"||node.text=="Function") {
                            orginnode = $("#webstructure").tree("find",controllid);
                        } else {
                            orginnode = $("#webstructure").tree("find",ocontrollid);
                        }

                        if (orginnode.data.event&&orginnode.data.event.length>0) {
                            var events = orginnode.data.event;
                            $.each(events,function(i,value){
                                for (var o in value) {
                                    if (value[o]==node.text) {
                                        js = value.fcontent;
                                    }
                                }
                            });
                        }

                        $("#inputor").val(js);
                        // window.frames["inputor"].the.editor.setValue(js);

                        var head = "function "+controllid+"."+eventis+"("+node.params+"){";
                        var foot = "}";
                        $("#f-head").html(head);
                        $("#f-foot").html(foot);
                    } 
                    else {
                        $("#inputor").val("");
                        $("#f-head").html("");
                        $("#f-foot").html("");
                    }
                }
            });
            $.loadnoticesbyscripttree();
        },

        createtypetree: function(o){
            var tree = [];
            $.each(modulejsona,function(i,value){
                var type = value.type;
                var parray = [];
                if (value.property&&value.property.length>0) {
                    var propertys = value.property;
                    $.each(propertys,function(j,val){
                        parray.push({
                            id: type+"-"+val.name,
                            text:type+"-"+val.name,
                            property:val
                        });
                    });
                }

                if (value.item) {
                    var items = value.item;
                    $.each(items,function(j,val){
                        parray.push({
                            id: type+"-item-"+val.name,
                            text:type+"-item-"+val.name,
                            property:val
                        });
                    });
                }

                var c = [];

                var p = {
                    id:type-"property",
                    text:"property",
                    children:parray
                };

                c.push(p);

                var earray = [];
                if (value.event&&value.event.length>0) {
                    var events = value.event;
                    $.each(events,function(j,val){
                        earray.push({
                            id: type+"-"+val.name,
                            text:type+"-"+val.name,
                            events:val
                        });
                    });
                }

                var e = {
                    id:type-"event",
                    text:"event",
                    children:earray
                };

                c.push(e);

                json = {
                    id:type,
                    text:type,
                    children:c
                };
                tree.push(json);
            });

            $(o).tree({
                data: tree
            });
        }
    };

    function getQueryString(name) { 
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
        var r = window.location.search.substr(1).match(reg); 
        if (r != null) return unescape(r[2]); return null; 
    } 
    function getcontrollbyevents(datas) {//根据原始树，加载拥有事件的控件树
        var c = getallcontroll(datas);
        var c1 = addeventtocontroller(c);
        var load = {
            id:"UI_load",
            text:"load",
            params:"e",
            iconCls:"icon-event"
        };
        var func = {
            id:"UI_resource",
            text:"Function",
            params:"e",
            iconCls:"icon-function"
        };
        c1.unshift(load,func);
        var tree = [{
            id:"UI",
            text:"UI",
            iconCls:"icon-ui",
            children: c1
        }];
        return tree;
    }

    function addeventtocontroller(datas) {
        var treedata = [];

        $.each(datas,function(i,value){
            if(geteventbytype(modulejsona,value)) {//有事件则添加到序列中
                treedata.push(geteventbytype(modulejsona,value));
            }
        });
        return treedata;

    }

    function geteventbytype(modulejsona,node) {
        var noden;
        $.each(modulejsona, function(i, value) {
            if (value.type == node.data.attr.type) {
                if (value.event&&value.event.length>0) {

                    var events = [];

                    $.each(value.event,function(j,value1){
                        var params = "";
                        if (value1.params) {
                            params = value1.params;
                        }
                        events.push({ 
                            id:node.id+"_"+value1.name,
                            text:value1.name,
                            params:params,
                            iconCls:"icon-event",
                            oid:node.oid
                        });
                    });

                    noden = {
                        id: node.id,
                        text: node.text,
                        data: node.data,
                        children: events,
                        iconCls:node.iconCls,
                        state:"closed",
                        oid: node.oid
                    };
                }
            }
        });
        return noden;
    }

    function getallcontroll(datas) {
        var controlls = [];
        $.each(datas,function(i,value){
            var nid;
            var pro = value.data.property;
            $.each(pro,function(j,n){
                if (n.name=="id") {
                    nid = n.content;
                }
            });
            controlls.push({
                id:nid,
                text:value.text,
                data:value.data,
                iconCls:value.iconCls,
                oid:value.id
            });
            if (value.children&&value.children.length>0) {
                var child = value.children;
                var c1 = getallcontroll(child);
                for (var j=0; j < c1.length; j++) {
                    controlls.push( c1[j] );
                }
            }
        });
        return controlls;
    }

    $("body").getpoolxml("inint");
    $("body").getpoolxml("getcontrolbytree");
    $("#typestructure").getpoolxml("createtypetree");
    
})(jQuery);

$(function() {

    $("#webstructure").tree({
        onLoadSuccess: function(node, data) {
            loadwebdisigner(data);
            parsejsontoxml();
        },
        onSelect: function(node) {
            // selectarea(node);
            loadpropertys(node);
        }
    });

    function selectarea(node) { 
        $("body").find(".u-focus").removeClass("u-focus");
        $("#"+node.id).addClass("u-focus");
    }

    $("#Swebstructure").tree({
        onSelect: function(node) {
            var id = node.id;
            var node = $("#webstructure").tree("find",id);

            try {
                $("#webstructure").tree("select",node.target);
            }catch(err) {
                console.error(err);
                return true;
            }

            if ($("#"+id).length>0) {
                if ($(".u-focus").length<=1) {
                    $(".u-focus").removeClass("u-focus");
                }
                $("#"+id).addClass("u-focus");
            }
        },
        onDblClick: function(node) {
            $("#Swebstructure").tree("select",node.target);
            var type = node.data.attr.type;
            if (type=="form") {
                // loadeditdesinger();
                // $("#addselectdialog").dialog("open");
            }
            else if (type=="datagrid"){
                var treenode = $("#webstructure").tree("getSelected");
                loadeditdesinger1(treenode);
                $("#addtablecolumn").dialog("open");
            }
            else if (type=="tabs") {
                var treenode = $("#webstructure").tree("getSelected");
                loadeditdesinger2(treenode);
                $("#edittab").dialog("open");
            }
            else if (type=="html") {
                var sn = $("#webstructure").tree("getSelected");
                var t = "";
                if (sn.data.property&&sn.data.property.length>0) {
                    var p = sn.data.property;
                    $.each(p,function(i,c){
                        if (c.name=="content") {
                            t = c.content;
                        }
                    });
                }
                t = t.replace("<script><![CDATA[","");
                t = t.replace("]]></script>","");
                UE.getEditor('editor').setContent(t, false);
                $("#htmleidt").dialog("open");
            }
        },
        onContextMenu:   function(e,node) {
            e.preventDefault();
            $("#Swebstructure").tree("select",node.target);
            $('#mm').menu('show',   {
                left:  e.pageX,
                top:  e.pageY
            });
            return false;
        }
    });
});

function getroworcolbynode(node,ff) {
    var rs;
    if (node.data.property&&node.data.property.length>0) {
        var pro = node.data.property;
        $.each(pro,function(i,c){
            if (c.name==ff) {
                rs = c.content;
            }
        });
    }
    return rs;
}

function menuHandler(item){
    var treenode = $("#webstructure").tree("getSelected");
    var type = treenode.data.attr.type;
    if (item.text == "Delete") {//右键删除节点，此时一并去掉隐藏的树节点

        var id = treenode.id;

        var $Sstr = $("#Swebstructure");
        var node = $Sstr.tree("find",id);
        $Sstr.tree("remove",node.target);

        var $str = $("#webstructure");
        var node1 = $str.tree('find', id);//找到隐藏树的对应节点
        $str.tree("remove",node1.target);//将其删除

        
        var datas = $str.tree("getRoots");
        loadwebdisigner(datas);
        parsejsontoxml();
    } 
    else if (item.text == "merge") {
        var $f = $(".u-focus");
        var id = $f[0].id;
        var len = $f.length;
        var pa = $f.parent();

        var nid,a=[];
        $.each(pa,function(i,c){
            var $f1=$(c).find(".u-focus");
            var id1 = $f1.eq(0)[0].id;
            $.mergecell(id1,"col",$f1);
            if (i==0) {
                nid = id1;
            }
            a.push($("#"+id1)[0]);
        });
        $.mergecell(nid,"row",a);

        parsejsontoxml();

        var data = $("#webstructure").tree("getRoots");
        loadwebdisigner(data);
        $("body").getpoolxml("loadSwebstructure");
        $("body").getpoolxml("getcontrolbytree");
    }
    else if (item.text == "addrow") {
        var $f = $(".u-focus");
        var id = $f[0].id;
        var $t = $("#webstructure");
        var n = $t.tree("find",id);
        var np = $t.tree("getParent",n.target);
        var npp = $t.tree("getParent",np.target);

        var col = getroworcolbynode(npp,"column");
        // if(allowaddornot(col,np)) {

        // }

        // var col;
        // if (npp.data.property&&npp.data.property.length>0) {
        //     var pro = npp.data.property;
        //     $.each(pro,function(i,c){
        //         if (c.name=="column") {
        //             col = c.content;
        //         } else if (c.name=="row") {
        //             c.content = parseFloat(c.content) + 1;
        //         }
        //     });
        // }

        var child = [];
        if (col) {
            for (var i = 0; i < col; i++) {
                child.push({
                    "type": "layout.grid.column",
                    "seq": i
                });
            }
        }

        var params = {
            "struct": [{
                "type": "layout.grid.row",
                "seq": 0,
                "child": child
            }],
            "number": {
                "layout.grid.row": 1,
                "layout.grid.column": col
            }
        };

        var nnode = $.getnodesbyparm(params)[0];

        $t.tree("insert",{
            before:np.target,
            data:[nnode]
        });
        parsejsontoxml();
        var data = $("#webstructure").tree("getRoots");
        loadwebdisigner(data);
        $("body").getpoolxml("loadSwebstructure");
    }
    else if (item.text == "addcolumn") {
        var $f = $(".u-focus");
        var id = $f[0].id;
        var $t = $("#webstructure");
        var n = $t.tree("find",id);
        var np = $t.tree("getParent",n.target);
        var npp = $t.tree("getParent",np.target);

        var row = getroworcolbynode(npp,"row");

        // var row;
        // if (npp.data.property&&npp.data.property.length>0) {
        //     var pro = npp.data.property;
        //     $.each(pro,function(i,c){
        //         if (c.name=="row") {
        //             row = c.content;
        //         } else if (c.name=="column") {
        //             c.content = parseFloat(c.content) + 1;
        //         }
        //     });
        // }

        var child = [];
        if (row) {
            for (var i = 0; i < row; i++) {
                child.push({
                    "type": "layout.grid.column",
                    "seq": i
                });
            }
        }

        var params = {
            "struct": child,
            "number": {
                "layout.grid.column": row
            }
        };

        
        

        var nnode = $.getnodesbyparm(params);


        var index;
        if (np.children&&np.children.length>0) {
            $.each(np.children,function(i,c){
                if (c.id == id) {
                    index = i;
                }
            });
        }

        if (npp.children&&npp.children.length>0) {
            $.each(npp.children,function(i,c){
                var r = c.children;
                $.each(r,function(j,nell){
                    if (j==index) {
                        $t.tree("insert",{
                            before:nell.target,
                            data:[nnode[i]]
                        });
                    }
                });

            });
        }

        parsejsontoxml();
        var data = $("#webstructure").tree("getRoots");
        loadwebdisigner(data);
        $("body").getpoolxml("loadSwebstructure");
    }
}

var loadwebdisigner = function(data) { //根据tree加载设计页面
    var htmlobj = $.ajax({ url: "static/js/layout.json", async: false });
    var controll = JSON.parse(htmlobj.responseText);
    var ids = [];

    function getElementbytype(type,value) {
        var rs;
        rs = controll[type];//根据控件类型找到对应展示方式
        return linktodata(rs,value);//对展示方式进行后期处理（添加id等）
    }

    function getwidthbycol(child,index) {
        var w,f=true;
        if (child&&child.length>0) {
            $.each(child,function(i,c){
                if (c.children&&c.children.length>0) {
                    if (c.children[index]) {
                        var col = c.children[index];
                        var p = col.data.property;
                        $.each(p,function(j,n){
                            if (n.name=="width"&&f){
                                w = n.content;
                                f=false;
                            }
                        });
                    }
                }
            });
        }
        if (!w) {
            w = "auto";
        }
        return w;
    }

    function linktodata(rs,value) {
        rs = $(rs).attr("id",value.id)[0].outerHTML;

        // 只允许form及布局的单元格内刚入内容
        // if(value.data.attr.type&&value.data.attr.type=="layout.grid.column") {
            ids.push(value.id);
        // }

        var type = value.data.attr.type;

        var propertys = value.data.property;

        if (type=="button") {
            $.each(propertys,function(i,value){
                if(value.name == "text") {
                    rs = $(rs).find("button").text(value.content).parents(".u-cell")[0].outerHTML;
                }
            });
        } else if ($.getctbyc(type)=="inputControls") {

            var lable;

            $.each(propertys,function(i,value){
                if (value.name=="label") {
                    lable = value.content;
                }
            });

            rs = $(rs).find("label").text(lable).parents(".u-cell")[0].outerHTML;
        }

        if (type=="datagrid.item") {

            var header;

            $.each(propertys,function(i,value){
                if (value.name=="header") {
                    header = value.content;
                }
            });

            rs = $(rs).text(header)[0].outerHTML;
        }

        if (type=="layout.grid"||type=="form") {
            var c;
            if (type=="layout.grid") {
                rs = $(rs).append("<colgroup></colgroup>")[0].outerHTML;
            } else if (type=="form") {
                rs = $(rs).find("table").append("<colgroup></colgroup>").parents(".u-form")[0].outerHTML;
            }
            
            $.each(propertys,function(i,value){
                if (value.name=="column") {
                    c = value.content;
                }
            });

            for (var i = 0; i < c; i++) {
                var w = getwidthbycol(value.children,i);
                if (type=="layout.grid") {
                    rs = $(rs).find("colgroup").append("<col style=\"width:"+w+";\">").parents("table")[0].outerHTML;
                } else if (type=="form") {
                    rs = $(rs).find("colgroup").append("<col style=\"width:"+w+";\">").parents(".u-form")[0].outerHTML;
                }
            }
        }

        if (type=="layout.grid.column") {
            var style = "";
            var rowspan="",colspan="";
            $.each(propertys,function(i,value){
                if (value.name=="rowspan") {
                    rowspan = value.content;
                } else if (value.name=="colspan") {
                    colspan = value.content;
                } else {
                    style += value.name + ":" + value.content+";";
                }
            });
            rs = $(rs).attr("rowspan",rowspan);
            rs = $(rs).attr("colspan",colspan);
            rs = $(rs).attr("style",style)[0].outerHTML;
        }

        return rs;
    }


    function splitbychildren(o) {
        var nxml = "";
        $.each(o, function(i, value) {
            var inner = "";
            var children = value.children;
            if (typeof children !== 'undefined' && children.length > 0) {
                inner = splitbychildren(children);
            }
            var type = value.data.attr.type;
            if (typeof type !== 'undefined') {
                var ccontent = getElementbytype(type,value);
                if (inner !== "") {
                    if (type == "datagrid") {
                        var cc = $(inner).length;
                        inner = "<thead>" +
                            inner +
                            "</thead><tbody><tr><td colspan=\""+cc+"\"></td></tr></tbody>";
                    }

                    if (type == "form"||type == "toolbar") {
                        ccontent = $(ccontent).find("table").append(inner).parent();
                    }
                    else {
                        ccontent = $(ccontent).append(inner);
                    }
                    ccontent = ccontent[0].outerHTML;
                }
                nxml += ccontent;
            }
        });
        return nxml;
    }

    var html = splitbychildren(data);
    $("#target").html(html);

    $.each(ids,function(i,value){
        if (value) {
            enabletodrop("#"+value);
        }
    });

    bindclickontable();
    desingercontextmenu();
    $("body").getpoolxml("getcontrolbytree");
}


var parsejsontoxml = function() {

    function createXml(str) {
        if (document.all) {　　
            var xmlDom = new ActiveXObject("Microsoft.XMLDOM");　　
            xmlDom.loadXML(str);　　
            return xmlDom;
        } else {
            return new DOMParser().parseFromString(str, "text/xml")
        }
    }



    function jsontoxml(jsonarray) {//将json转为xml,填充源码
        var nxml = "";
        $.each(jsonarray, function(i, value) {
            var id;
            var text = value.text;
            var type = value.data.attr.type;
            
            var inner = "";
            if (typeof value.children !== 'undefined'&&value.children.length>0) {
                inner = jsontoxml(value.children);
                if (value.data.attr.type == "datagrid") {
                    inner = "<group name=\"columns\">" +inner+ "</group>";
                }
            }

            var property = value.data.property;

            var property_str = "";

            $.each(property,function(i,value){
                if (value.name=="id") {
                    id = value.content;
                    // return true;
                }
                
                var $tt = $("#typestructure");
                var proper;
                if (type=="datagrid.item") {
                    proper = "datagrid-item-"+value.name;
                }
                else {
                    proper = type+"-"+value.name;
                }
                var option = "",vtype = "";

                var node = $tt.tree("find",proper);
                if (node&&node.property) {
                    var nodepro = node.property;
                    if (nodepro.option) {
                        option = "option=\""+nodepro.option+"\"";
                    }
                    if (nodepro.type) {
                        if(typeof(nodepro.type)=="object") {
                            vtype = "type=\"String\"";
                        } else {
                            vtype = "type=\""+nodepro.type+"\"";
                        }
                    }
                    if (!(nodepro.append&&nodepro.append=="1"&&nodepro.dv==value.content)){
                        property_str += "<property name=\""+value.name+"\" "+option+" "+vtype+">" + 
                        value.content +
                        "</property>";
                    }
                } else {
                    
                    property_str += "<property name=\""+value.name+"\" "+option+" "+vtype+">" + 
                    value.content +
                    "</property>";
                }

            });


            if (type=="layout.grid"||type=="form") {
                var a = [];
                if (type=="layout.grid") {
                    if ($("#"+id).children("colgroup").children("col")&&$("#"+id).children("colgroup").children("col").length>0) {
                        var col = $("#"+id).children("colgroup").children("col");
                        $.each(col,function(j,c){
                            a.push({
                                "style":$(c).attr("style")
                            });
                        });
                    }
                } else if (type=="form") {
                    if ($("#"+id).children("table").children("colgroup").children("col")&&$("#"+id).children("table").children("colgroup").children("col").length>0) {
                        var col = $("#"+id).children("table").children("colgroup").children("col");
                        $.each(col,function(j,c){
                            a.push({
                                "style":$(c).attr("style")
                            });
                        });
                    }
                }
                
                if (a.length>0) {
                    a = JSON.stringify(a);
                    a = a.substring(1,a.length-1);
                    property_str += "<property name=\"colgroup\" option=\"1\" type=\"String\" >" + 
                    a +
                    "</property>";
                }
            }
            

            if (type == "ui") {
                xml = "<ui displayName=\"BlankPage\" lastModified=\"0\">"+ 
                "</ui>";
                var eventtag = "<resource>"+
                    "<include></include>"+
                    "<style></style>"+
                    "<script></script>"+
                "</resource>";
                xml = $(xml).append(eventtag);
            } 
            else {
                xml = "<view";
                if (id) {
                    xml += " id=\"" + id + "\"";
                }
                if (id) {
                    xml += " name=\"" + id + "\"";
                }
                xml += " type=\"" + type + "\"";
                xml += "></view>";
            }

            



            if (value.data.event&&value.data.event.length>0) {
                var events = value.data.event;
                $.each(events,function(i,value){
                    for(var o in value) {
                        if (o=="fname"&&value[o]=="Function") {
                            if (value.fcontent!=="") {
                                $(xml).find("resource script").text("<![CDATA["+value.fcontent+"]]>");
                            }
                        }
                        else {
                            if (o=="fname"&&value.fcontent!=="") {

                                var $tt = $("#typestructure");
                                var event = type+"-"+value.fname;
                                var vtype = "";
                                var node = $tt.tree("find",event);
                                if (node&&node.events) {
                                    var nodepro = node.events;
                                    if (nodepro.type&&nodepro.type!=="") {
                                        vtype = "type=\""+nodepro.type+"\"";
                                    }
                                }

                                var params = "";
                                if (value.fparams!=="") {
                                    params = "<parameters>"+ value.fparams +"</parameters>";
                                }
                                var eventtag = "<event name=\""+value[o]+"\" "+vtype+">" +
                                "<script><![CDATA["+value.fcontent+"]]></script>" + params +"</event>";
                                xml = $(xml).append(eventtag);
                            }
                        }
                    }
                });
            }
            xml = $(xml).append(property_str+inner);
            xml = xml[0].outerHTML;
            nxml += xml;
        });
        return nxml;
    }

    //convert xml object to string
    function XML2String(xmlObject) {
    // for IE
        if (window.ActiveXObject) {       
            return xmlObject.xml;
        }
    // for other browsers
        else {        
            return (new XMLSerializer()).serializeToString(xmlObject);
        }
    }


    function getxml() {
        var jsonarray = $("#webstructure").tree("getRoots");
        var xml = jsontoxml(jsonarray);
        return xml;
    }

    var a = getxml();

    a = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + a;
        
    $("#source_code").textbox("setValue",a);

}

$.parser.onComplete = function() {
    $("body").css("visibility", "visible");
}


