import { Sequelize } from 'sequelize';

const sequelize = new Sequelize({
  dialect: 'sqlite',
  storage: '\\SiteTeste\\site.sqlite',
});

export default sequelize;