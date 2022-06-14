import {REST} from  './rest.js';
import {VIEW} from  './view.js';
import {ROUTE} from './route.js';

function loadAll() {
    // Load all posts
    REST.get('/post/', (status, response)=> {
        if (status != 200)
            throw `Error get posts. HTTP status: ${status}`;
        VIEW.loadPosts(JSON.parse(response));      
    });
    
    // Load all categories
    REST.get('/category/', (status, response)=> {
        if (status != 200)
            throw `Error get categories. HTTP status: ${status}`;
        VIEW.loadCategories(JSON.parse(response));
    });
}

function loadPost(postId) {
    REST.get(`/post/${postId}/`, (status, response)=> {
        if (status != 200)
            throw `Error get post. HTTP status: ${status}`;
        VIEW.loadPost(JSON.parse(response));
    });
}

function loadCategory(categoryId) {
    REST.get(`/category/${categoryId}`, (status, response)=> {
        if (status != 200)
            throw `Error get category. HTTP status: ${status}`;
        VIEW.loadCategory(JSON.parse(response));
    });
}

function getAboutMySelf() {
    REST.get(`/about/`, (status, response)=> {
        if (status != 200)
            throw `Error get about. HTTP status: ${status}`;
        VIEW.htmlShow(response);
    });
}

function getContact() {
    REST.get(`/contact/`, (status, response)=> {
        if (status != 200)
            throw `Error get contact. HTTP status: ${status}`;
        VIEW.htmlShow(response);
    });
}

window.onload = function() {

    ROUTE.setMainPage(loadAll);

    ROUTE.addPrefixHandler('post_', loadPost);
    ROUTE.addPrefixHandler('category_', loadCategory);

    ROUTE.setPageHandler('about', getAboutMySelf);
    ROUTE.setPageHandler('contact', getContact);

    ROUTE.run();
};