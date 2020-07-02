// load up new route
const translateRoutes = require("./translates");

const appRouter = (app, fs) => {
    // default route here
    app.get("/", (req, res) => {
        res.send("welcome to the development api-server");
    });

    //specific routes
    translateRoutes(app, fs);
};

module.exports = appRouter;