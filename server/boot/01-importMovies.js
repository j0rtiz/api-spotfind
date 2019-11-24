'use strict';

const cheerio = require('cheerio');
const axios = require('axios');

module.exports = async (app) => {
  const {Sessao} = app.models;

  const moviesIds = await getMoviesIds();
  const movies = await getMovies(moviesIds);
  const count = await Sessao.count();

  if (count === 0) {
    return Sessao.create(movies)
      .then(movies => console.log(movies.length + ' filmes foram importados!'));
  }

  return console.log('Nenhum filme foi importado!');
};

async function getMoviesIds() {
  const mostPopularUrl = 'https://www.imdb.com/chart/moviemeter/?ref_=nv_mv_mpm';
  const moviesIds = await axios.get(mostPopularUrl)
    .then((res) => {
      const $ = cheerio.load(res.data);
      const links = $('a');
      const ids = [];

      $(links).each((i, a) => {
        const link = $(a).attr('href');
        const id = link && link.split('/')[1] === 'title' ? link.split('/')[2] : null;

        if (id && !ids.includes(id)) ids.push(id);
      });

      return ids;
    })
    .catch(() => []);

  return moviesIds;
}

async function getMovies(moviesIds) {
  const omdb = 'http://www.omdbapi.com/?apikey=31fcd18f&y=2019&type=movie&i=';
  const movies = moviesIds.map((movieId) => {
    return new Promise((resolve, reject) => {
      return axios.get(omdb + movieId)
        .then((res) => {
          const {Title: titulo, Year: ano, Genre: genero, Actors: atores, Poster: poster, imdbID: imdbId, Type: tipo} = res.data;
          const movie = {titulo, ano, genero, atores, poster, imdbId, tipo, local: 'Canoas Shopping', valor: 30};

          resolve(movie);
        })
        .catch(() => reject(null));
    });
  });

  return Promise.all(movies);
}
