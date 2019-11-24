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
  const places = ['CineBancários', 'GNC Iguatemi Porto Alegre', 'Cinemark Barra Sul', 'Cinemark Wallig', 'Cinépolis João Pessoa - POA', 'Cineflix Shopping Total', 'Cinemark Bourbon Ipiranga', 'GNC Lindóia', 'GNC Cinemas Moinhos', 'Espaço Itaú de Cinema - POA', 'GNC Praia de Belas'];
  const omdb = 'http://www.omdbapi.com/?apikey=31fcd18f&y=2019&type=movie&i=';
  const movies = moviesIds.map((movieId) => {
    return new Promise((resolve, reject) => {
      return axios.get(omdb + movieId)
        .then((res) => {
          const {Title: titulo, Year: ano, Genre: genero, Actors: atores, Poster: poster, imdbID: imdbId, Type: tipo} = res.data;
          const local = getRandomName(places);
          const valor = getRandomInt(20, 40);
          const movie = {titulo, ano, genero, atores, poster, imdbId, tipo, local, valor};

          resolve(movie);
        })
        .catch(() => reject(null));
    });
  });

  return Promise.all(movies);
}

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);

  return (Math.random() * (max - min + 1)) + min | 0;
}

function getRandomName(names) {
  return names[(Math.random() * names.length) | 0];
}
