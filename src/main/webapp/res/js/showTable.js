function showTable(tag) {
    var id = document.getElementById(tag).value;
    var arr = document.getElementsByClassName('table1');
    for(var i = 0;i<arr.length;i++) {
        arr[i].style.display = "none";
    }
    id = id+tag;
    document.getElementById(id).style.display = "block";
}