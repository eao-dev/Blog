export {ROUTE};

let pageMap = new Map();
let mainPageFn = null;
let otherFn = null;

const ROUTE = {
    setMainPage:setMainPage,
    setPageHandler:setPageHandler,
    setOtherHandler:setOtherHandler,
    run:run
};

function setMainPage(func) {
    mainPageFn = func;
}

function setPageHandler(page, func) {
    pageMap.set(page, func);
}

function setOtherHandler(func) {
    otherFn = func;
}

function routeHandle() {

    let hash = window.location.hash;
    if (hash != '') {
        let page = hash.replace('#', '');

        if (otherFn != null) {
            if (otherFn(page))
                return;
        }
            
        // Handle page
        for (const pageMapItem of pageMap.entries()) {
            if (pageMapItem[0] === page) {
                pageMapItem[1](page); // Call function
                return;
            }
        }
    }

    // Page not foun => load default page
    mainPageFn();
}

function run() {

    window.addEventListener('hashchange', routeHandle);

    // Handle current page
    routeHandle();

}