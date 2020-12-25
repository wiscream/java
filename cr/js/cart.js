let items=document.querySelectorAll('.item');
function updatePrice(){
    let totalNum=0;
    let totalPrice=0;
    items.forEach(function (item) {
        if (item.querySelector('.item_checkbox').checked){
            let num=item.querySelector('.num').value;
            totalNum=totalNum+parseInt(num);
            let price=item.querySelector('.price_2').getAttribute('data-price');
            totalPrice=totalPrice+parseFloat(price)*num;
        }
    });

    document.querySelector('.checked_num').innerText=totalNum;
    document.querySelector('.total_price').innerText=totalPrice;
}

window.onload=function (){
    let itemCheckboxes=document.querySelectorAll('.item_checkbox');
    itemCheckboxes.forEach(function (itemCheckbox) {
        itemCheckbox.onchange = function () {
            updatePrice();
        }
    });
    // 商品数量
    let minuses=document.querySelectorAll('.num_minus');
    minuses.forEach(function (minus) {
        minus.onclick=function (event) {
            let num=minus.parentElement.querySelector('.num').value;
            if(parseInt(num)>1){
                minus.parentElement.querySelector('.num').value=parseInt(num)-1;
                updatePrice();
            }
        };
    });
    let pluses=document.querySelectorAll('.num_plus');
    pluses.forEach(function (plus) {
        plus.onclick=function (event) {
            let num=plus.parentElement.querySelector('.num').value;
                plus.parentElement.querySelector('.num').value=parseInt(num)+1;
                updatePrice();
        };
    });
    // 全选
    let checkedAll=document.querySelector(".checked_box");
    checkedAll.onclick=function () {
        if (checkedAll.checked){
            let checkedShop=document.querySelector('.shop_checkbox');
            checkedShop.checked=true;
        }
    }
    //删除
    let operation=document.querySelector('.operation');
        operation.onchange=function(){
            operation.parentNode.parentNode.removeEventListener(items);
    }
}