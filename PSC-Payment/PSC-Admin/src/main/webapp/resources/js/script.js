$.fn.atwho.debug = true

function loadcontrollers(at, emojis) {
    var emojis = $.map(emojis, function(value, i) {
        return { key: value, name: value }
    });
    var emoji_config = {
        at: at,
        data: emojis,
        displayTpl: "<li>${name}</li>",
        insertTpl: at + '${key}',
        delay: 400,
        limit: 100
    }
    $inputor = $('#inputor').atwho(emoji_config);
}

function getcontrollerbytype(type, modulejsona) {
    var rs;
    $.each(modulejsona, function(i, value) {
        var ntype = value.type;
        if (type == ntype) {
            rs = value;
        }
    });
    return rs;
}

function getchildbynode(rs,ds) {
    if (rs.length>0) {
        $.each(rs,function(i,c){
            ds.push(c);
            if (c.children&&c.children.length>0) {
                getchildbynode(c.children,ds);
            }
        });
    }
    return ds;
}

function getallnodefromotree(){
    var $t = $("#webstructure");
    var rs = $t.tree("getRoots");
    var ds = [];
    var ds = getchildbynode(rs,ds);
    return ds;
}

function loadnoticesbyscripttree() {
    var datas = getallnodefromotree();
    var module = $.ajax({ url: "static/js/module1.json", async: false });
    var modulejsona = JSON.parse(module.responseText);
    $.each(datas, function(i, value) {
        var p = value.data.property;
        if (p&&p.length>0) {
            var id;
            $.each(p,function(n,v){
                if(v.name=="id") {
                    id = v.content + ".";
                }
            });
            if (id) {
                var type = value.data.attr.type;
                var c = getcontrollerbytype(type, modulejsona);
                if (c && c.method && c.method.length > 0) {
                    var m = c.method;
                    var na = [];
                    $.each(m, function(j, v) {
                        var n = v.name;
                        na.push(n);
                    });
                    loadcontrollers(id, na);
                }
            }
        }
    });

    loadcontrollers("ui.",["ajax(trancode,param,callback)","openwin(url,width,height,title)","closewin()"]);
}

var loadtreebydesiger2 = function() { //根据添加内容加载树
    var $tree = $("#Swebstructure");
    var $otree = $("#webstructure");
    var treenode = $otree.tree("getSelected");
    var id = treenode.id;
    var parent = $otree.tree("getParent", treenode.target);

    var datas = $("#tabgrid").edatagrid("getData").rows;

    // 删除初次的搜索条件
    var n1 = $otree.tree("find", id);
    if (!$otree.tree("isLeaf",n1.target)) {
        var ns = $otree.tree("getChildren",n1.target);
        if (ns.length>0) {
            $.each(ns,function(i,c){
                $otree.tree("remove", c.target);
            });
        }
    }
    
    
    // 添加编辑后的搜索条件

    var array = [];

    $.each(datas, function(i, value) {
        var type = "tabitem";
        var node = {
            id: type+i,
            text: value.tn + "(" + type + ")",
            iconCls:"icon-"+type.replace(/\./g, "-"),
            data: {
                attr: {
                    type: type
                },
                property: [{
                    content: type+i,
                    name:"id"
                }, {
                    content: value.tn,
                    name: "title"
                }, {
                    content: value.href,
                    name: "href"
                }],
                event: {}
            }
        }

        array.push(node);
    });

    $otree.tree('append', {
        parent: treenode.target,
        data: array
    });

    $("body").getpoolxml("loadSwebstructure");
}

function saveitems2() {
    var $grid = $("#tabgrid");
    var selected = $grid.edatagrid("getSelected");
    var index = $grid.edatagrid("getRowIndex",selected);
    $grid.edatagrid("endEdit",index);


    $("#edittab").dialog("close");
    loadtreebydesiger2();
    var data = $("#webstructure").tree("getRoots");
    loadwebdisigner(data);
    $("body").getpoolxml("getcontrolbytree");
}

function closeastdialog2() {
    $("#edittab").dialog("close");
}

function addRow2() {
    $("#tabgrid").edatagrid('addRow');
}

function deleteRow2() {
    var row = $("#tabgrid").edatagrid("getSelected");
    var index = $("#tabgrid").edatagrid("getRowIndex", row);
    $("#tabgrid").edatagrid("deleteRow", index);
}

function addRow3() {
    $("#columngrid").edatagrid('addRow');
}

function deleteRow3() {
    var row = $("#columngrid").edatagrid("getSelected");
    var index = $("#columngrid").edatagrid("getRowIndex", row);
    $("#columngrid").edatagrid("deleteRow", index);
}

$(document).keydown(function(e){
    if( e.ctrlKey  == true && e.keyCode == 83 ){
        savexml();
        return false; 
    } else if (e.ctrlKey  == true && e.keyCode == 80) {
        preview();
    }
});




