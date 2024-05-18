import express from 'express';
import Sorvete from '../model/SorveteriaModel.js';

const sorvete = express();
sorvete.use(express.json());

sorvete.get('/sorvetes', async (req, res) => {
  try {
    
    const sorvetesDoBanco = await Sorvete.findAll();
    
    const mensagem = sorvetesDoBanco.map(sorvete => ({
      id: sorvete.id,
      nome: sorvete.nome,
      quantidade: sorvete.quantidade,
      preco:sorvete.preco
    }));

    res.status(200).json(mensagem);
  } catch (erro) {
    console.error(erro);
    res.status(500).json({ erro: 'Erro interno do servidor' });
  }
});

sorvete.get('/sorvete/nome/:id', async (req, res) => {
  try {
    const sorveteId = req.params.id;

    const sorveteEncontrado = await Sorvete.findByPk(sorveteId, {
      attributes: ['nome']
    });

    if (!sorveteEncontrado) {
      return res.status(404).json({ erro: 'Sorvete não encontrado' });
    }

    res.status(200).json({ nome: sorveteEncontrado.nome });
  } catch (erro) {
    console.error(erro);
    res.status(500).json({ erro: 'Erro interno do servidor' });
  }
});





sorvete.post('/sorvetes',async (req, res) => {
    try {
      const { nome, quantidade,preco } = req.body;
  
      const novoSorvete = await Sorvete.create({ nome, quantidade,preco });
        
      res.status(201).json({
        mensagem: 'Sorvete criado com sucesso!',
        id: novoSorvete.id,
        nome: novoSorvete.nome,
        quantidade: novoSorvete.quantidade,
        preco:novoSorvete.preco
      });

    } catch (erro) {
      console.error(erro);
      res.status(500).json({ erro: 'Erro interno do servidor' });
    }
  });
  
  sorvete.delete('/sorvetes/:id',async (req, res) => {
    try {
      const sorveteId = req.params.id;

      const sorveteToDelete = await Sorvete.findByPk(sorveteId);
      if (!sorveteToDelete) {
        return res.status(404).json({ mensagem: 'Sorvete não encontrado',flag:false });
      }
  
      await sorveteToDelete.destroy();
  
      return res.status(200).json({ mensagem: 'Sorvete excluído com sucesso',flag:true });
    } catch (erro) {
      console.error(erro);
      res.status(500).json({ erro: 'Erro interno do servidor',flag:false });
    }
  });


  export default sorvete;