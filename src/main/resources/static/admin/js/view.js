export { VIEW };

const VIEW = {
    loadPosts: loadPosts,
    loadCategories: loadCategories,
    
};

function loadPosts(posts) {

    let postRemoveList = document.getElementById('postRemoveList');
    if (postRemoveList==null)
        throw "'postRemoveList' not found";

    postRemoveList.innerHTML = '';

    for (const post of posts) {
        let option = document.createElement('option');
        option.value=post.id;
        option.textContent=post.title;
        postRemoveList.appendChild(option);
    }


}
function loadCategories(categoriesObject) {


    let categoryRemoveList = document.getElementById('categoryRemoveList');
    if (categoryRemoveList==null)
        throw "'categoryRemoveList' not found";

    categoryRemoveList.innerHTML = '';

    let newPostCategoryList = document.getElementById('newPostCategoryList');
    if (categoryRemoveList==null)
        throw "'newPostCategoryList' not found";

    newPostCategoryList.innerHTML = '';

    for (const category of categoriesObject) {
        let option = document.createElement('option');
        option.value=category.id;
        option.textContent=category.name;
        categoryRemoveList.appendChild(option);

        let checkbox = document.createElement('input');
        checkbox.className = 'customCheckbox';
        checkbox.type = 'checkbox';
        checkbox.value = category.id;

        let label = document.createElement('label');
        label.textContent = category.name;

        newPostCategoryList.appendChild(checkbox);
        newPostCategoryList.appendChild(label);
        newPostCategoryList.appendChild(document.createElement('br'));
        
    }
    
    
}