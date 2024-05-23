import { Sequelize } from 'sequelize';

const sequelize = new Sequelize('sorveteria', 'sorveteria_owner', 'jJ3PiFHhxyW2', {
  dialect: 'postgres',
  host: 'ep-little-forest-a5j3tnvn.us-east-2.aws.neon.tech',
  ssl: true,
  dialectOptions: {
    ssl: {
      require: true
    }
  }
});

export default sequelize;
