'use strict';

module.exports = (app, cb) => {
  const lbTables = {
    db: [
      'ACL',
      'RoleMapping',
      'Role',
      'AccessToken',
      'Usuario',
      'Sessoes',
    ],
  };

  console.log('\x1b[35m\n%s\n\x1b[0m', 'Atualizando o banco de dados...');

  app.dataSources.db.autoupdate(lbTables.db, err => {
    if (err) throw err;
  });

  process.nextTick(cb);
};
