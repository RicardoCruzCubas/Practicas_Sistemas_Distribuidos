import { Layout, Home, About, Gallery } from "./components";
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="galeria" element={<Gallery />} />
        <Route path="acerca" element={<About />} />
      </Routes>
    </Layout>
  );
}

export default App;
