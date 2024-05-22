import cors from 'cors';
import sorvete from './src/Controllers/Sorveteria.js';
import app from "./src/index.js";
const port = 3000;

app.use(cors());

app.listen(port,()=>
{
    console.log(`Servidor rodando no endereço http://localhost:${port}`);
})
app.use(sorvete);

app.get('/',(req,res)=>
{
    res.status(200).send("Sistema de sorveteria em node js");
})