function minSalaryLessThanMax() {
    let minSalary = parseFloat(document.getElementById("min").value);
    let maxSalary = parseFloat(document.getElementById("max").value);
    if (minSalary > maxSalary) {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Минимальная зарплата больше максимальной",
            type: "warning",
            backdrop: `rgba(0,0,123,0.4) `
        });
        return false;
    }
    return true;
}

function checkEmployeeForm() {
    let name = document.getElementById("name").value;
    let depId = document.getElementById("depId").value;
    let salary = document.getElementById("salary").value;
    if (name == "") {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Имя сотрудника не заполнено",
            type: "warning",
            backdrop: `rgba(0,0,123,0.4) `
        });
        return false;
    }
    else if (depId == "--") {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Выберите отдел",
            type: "warning",
            backdrop: `rgba(0,0,123,0.4) `
        });
        return false;
    }
    else if (salary == "") {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Зарплата не заполнена",
            type: "warning",
            backdrop: `rgba(0,0,123,0.4) `
        });
        return false;
    }
    return true;
}

function checkDepartmentForm() {
    let name = document.getElementById("name").value;
    if (name == "") {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Имя отдела не заполнено",
            type: "warning",
            backdrop: `rgba(0,0,123,0.4) `
        });
        return false;
    }
    return true;
}

function checkUserForm() {
    let name = document.getElementById("username").value;
    if (name == "") {
        Swal.fire({
            title: "Ошибка ввода!",
            text: "Имя пользователя не заполнено",
            type: "warning",
            backdrop: `rgba(0,0,123,0.4) `
        });
        return false;
    }
    return true;
}
