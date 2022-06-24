import {REST} from  './rest.js';
import {VIEW} from  './view.js';
import {ROUTE} from './route.js';

function loadAll() {
    REST.get('/post/', (response)=> {
        VIEW.loadPosts(JSON.parse(response));      
    });
    
    REST.get('/category/', (response)=> {
        VIEW.loadCategories(JSON.parse(response));
    });
}

function loadPost(postId) {
    REST.get(`/post/${postId}/`, (response)=> {
        VIEW.loadPost(JSON.parse(response));
    });
}

function loadCategory(categoryId) {
    REST.get(`/category/${categoryId}`, (response)=> {
        VIEW.loadCategory(JSON.parse(response));
    });
}

function getAbout() {
    REST.get(`/about/`, (response)=> {
        VIEW.htmlShow(response);
    });
}

function getContact() {
    REST.get(`/contact/`, (response)=> {
        VIEW.htmlShow(response);
    });
}

window.onload = function() {

    ROUTE.setMainPage(loadAll);

    ROUTE.addPrefixHandler('post_', loadPost);
    ROUTE.addPrefixHandler('category_', loadCategory);

    ROUTE.setPageHandler('about', getAbout);
    ROUTE.setPageHandler('contact', getContact);

    ROUTE.run();
};