// function addQuestion() {
//     var questionForm = document.getElementById("");
// }
//
//
//
// document.getElementById("add-new-option").addEventListener('click', function addOption() {
//     var optionForm = document.getElementsByTagName("tbody");
//     console.log(optionForm);
//     console.log('fffffffff');
// });


$("#add-new-option").click(function () {
    option = '<tr>' +
        '<td class="checkbox"><input class="form-checkbox" type="checkbox"></td>' +
        '<td><input class="form-control" type="text" placeholder="Enter option"></td>' +
        '<td style="width: 5%"><a class="btn btn-outline-dark" id="delete" type="button">Del</a></td>' +
        '</tr>';
    $('tbody').append(option);
    console.log('new option added');
});