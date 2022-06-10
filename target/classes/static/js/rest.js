export {REST};

function GET(resource, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE)
            func(xhr.status, xhr.response);
    };
    xhr.send();
}

function POST(resource, params, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE)
            func(xhr.status, xhr.response);
    };

    let formData = new FormData();
    for (const pair of params.entries()) 
        formData.append(pair[0], pair[1]);

    xhr.send(formData);
}

function PUT(resource, params, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE)
            func(xhr.status, xhr.response);
    };

    let formData = new FormData();
    for (const pair of params.entries()) 
        formData.append(pair[0], pair[1]);

    xhr.send(formData);
}

function DELETE(resource, func) {
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', resource, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE)
            func(xhr.status, xhr.response);
    };
    xhr.send();
}

const REST = {
    get:GET,
    post:POST,
    put:PUT,
    delete:DELETE
}; 