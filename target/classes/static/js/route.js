export {ROUTE};

let pageMap = new Map();
let mainPageFn = null;
let prefixHandler = new Map();

const ROUTE = {
    setMainPage:setMainPage,
    setPageHandler:setPageHandler,
    addPrefixHandler:addPrefixHandler,
    run:run
};

function setMainPage(func) {
    mainPageFn = func;
}

function setPageHandler(page, func) {
    pageMap.set(page, func);
}

function addPrefixHandler(prefix, func) {
    prefixHandler.set(prefix, func)
}

function routeHandle() {

    let hash = window.location.hash;
    if (hash != '') {
        let hashValue = hash.substring(1, hash.length);

         // Handle page
         for (const prefixItem of prefixHandler.entries()) {
            const prefix = prefixItem[0];
            if (hashValue.substring(0, prefix.length) == prefix) {
                const postfix = hashValue.replace(prefix, '');
                prefixItem[1](postfix); // Call function
                return;
            }
        }
            
        // Handle page
        for (const pageMapItem of pageMap.entries()) {
            if (pageMapItem[0] === hashValue) {
                pageMapItem[1]();
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