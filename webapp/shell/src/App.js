import React, { Suspense } from "react";
const Navbar = React.lazy(() => import("app1/Navbar"));
const Users = React.lazy(() => import("app1/Users"));
const Wine = React.lazy(() => import("app2/Wine"));
const Vineyard = React.lazy(() => import("app3/Vineyard"));

const App = () => {
  return (
      <div>
          <Suspense fallback={"loading..."}>
                <Navbar organizationName="Webapp" />
                <Users />
                <Wine />
                <Vineyard />
          </Suspense>
      </div>
  );
}


export default App;
