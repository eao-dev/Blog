export {REST};

var csrf_header = document.querySelector("meta[name='_csrf_header']").getAttribute('content');
var csrf_token = document.querySelector('meta[name="_csrf"]').getAttribute('content');

function getForm(params) {
    
    let formData = new FormData();
    for (const pair of params.entries()) 
        formData.append(pair[0], pair[1]);

    return formData;
}

function GET(resource, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status != 200)
                throw `Get error:${resource} . HTTP-status:${xhr.status}`;
            func(xhr.response);
        }
    };
    xhr.send();
}

function POST(resource, params, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status != 201)
                throw `POST error:${resource} . HTTP-status:${xhr.status}`;
            func(xhr.response);
        }
    };
    xhr.setRequestHeader(csrf_header, csrf_token);
    xhr.send(getForm(params));
}

function PUT(resource, params, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status != 200)
                throw `PUT error:${resource} . HTTP-status:${xhr.status}`;
            func(xhr.response);
        }
    };
    xhr.setRequestHeader(csrf_header, csrf_token);
    xhr.send(getForm(params));
}

function DELETE(resource, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status != 200)
                throw `DELETE error:${resource} . HTTP-status:${xhr.status}`;
            func(xhr.response);
        }
    };
    xhr.setRequestHeader(csrf_header, csrf_token);
    xhr.send();
}

const REST = {
    get:GET,
    post:POST,
    put:PUT,
    delete:DELETE,
}; 