const ul = document.createElement('ul');
const url = 'service/users';
const createNode = element => document.createElement(element);
const append = (parent, element) => parent.appendChild(element);

const clearUl = () => {
    while (ul.firstChild)
        ul.removeChild(ul.firstChild);
};

const convertToJson = responce => responce.json();

const fillUl = persons => {
    for (let index = 0; index < persons.length; index++) {
        document.body.appendChild(ul);
        let li = createNode('li');
        let span = createNode('span');
        span.innerHTML = `ID: ${persons[index].id}. NAME: ${persons[index].name}`;
        append(li, span);
        append(ul, li);
    }
};

const errorMessage = error => {
    alert('Request failed: ' + error);
};

const getUsers = () => {
    clearUl();
    fetch(url)
        .then(convertToJson)
        .then(fillUl)
        .catch(errorMessage);
};