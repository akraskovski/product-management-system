const ul = document.createElement('ul');
ul.className = 'my-ul';
const url = 'http://localhost:8081/service/users';
const createNode = element => document.createElement(element);
const append = (parent, element) => parent.appendChild(element);

const getUsers = () => {
    while (ul.firstChild)
        ul.removeChild(ul.firstChild);
    fetch(url)
        .then((responce) => responce.json())
        .then((persons) => {
            for (let index = 0; index < persons.length; index++) {
                document.body.appendChild(ul);
                let li = createNode('li');
                let span = createNode('span');
                span.innerHTML = `ID: ${persons[index].id}. NAME: ${persons[index].name}`;
                append(li, span);
                append(ul, li);
            }
        })
        .catch(function (error) {
            alert('Request failed: ' + error);
        });
};