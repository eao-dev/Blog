import {REST} from './rest.js';
import {VIEW} from './view.js';

function newCategory() {
    const params = new Map();
    params.set('name', document.getElementById('categoryNewName').value);
    REST.post('/category/create/', params, (response)=> {
        location.reload();
    });
};

function removeCategory() {
    const id = document.getElementById('categoryRemoveList').value;
    REST.delete(`/category/delete/${id}`, (response)=> {
        location.reload();
    });
};

function newPost() {
    let categories = new Array();
    const categoryListElements = document.getElementById('newPostCategoryList').children;

    for (const item of categoryListElements){
        if (item.nodeName === 'INPUT' && item.checked)
            categories.push(item.value);
    }

    const params = new Map();
    params.set('title',        document.getElementById('newPostTitle').value);
    params.set('tags',         document.getElementById('newPostTags').value);
    params.set('text',         document.getElementById('newPostText').value);
    params.set('description',  document.getElementById('newPostDescription').value);
    params.set('categories',   categories.toString());
    REST.post('/post/create/', params, (response)=> {
        location.reload();
    });
};

function removePost() {
    const id = document.getElementById('postRemoveList').value;
    REST.delete(`/post/${id}`, (response)=> {
        location.reload();
    });
};


window.onload = function() {

    // Load all posts
    REST.get('/post/', (response)=> {
        VIEW.loadPosts(JSON.parse(response));      
    });

    // Load all categories
    REST.get('/category/', (response)=> {
        VIEW.loadCategories(JSON.parse(response));
    });
    
    let buttonNewCtaegory = document.getElementById('categoryCreateButton');
    if (buttonNewCtaegory==null)
        throw `button 'buttonNewCtaegory' not found!`;

    buttonNewCtaegory.onclick = newCategory;

    let buttonNewPost = document.getElementById('newPostButton');
    if (buttonNewPost==null)
        throw `button 'newPostButton' not found!`;

    buttonNewPost.onclick = newPost;

    let buttonRemoveCategory = document.getElementById('categoryRemoveButton');
    if (buttonRemoveCategory==null)
        throw `button 'categoryRemoveButton' not found!`;

    buttonRemoveCategory.onclick = removeCategory;

    let buttonRemovePost = document.getElementById('postRemoveButton');
    if (buttonRemovePost==null)
        throw `button 'postRemoveButton' not found!`;

    buttonRemovePost.onclick = removePost;

};