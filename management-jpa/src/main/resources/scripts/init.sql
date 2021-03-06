
-- Actor data
INSERT INTO public.actor (firstname, lastname, birth_date, description) VALUES ('matej','kotnik', TO_DATE('30/11/1942', 'DD/MM/YYYY'), 'First class actor');
INSERT INTO public.actor (firstname, lastname, birth_date, description) VALUES ('mateja','pecnik', TO_DATE('30/11/1949', 'DD/MM/YYYY'), 'Second class actor');
INSERT INTO public.actor (firstname, lastname, birth_date, description) VALUES ('katarina','hribar', TO_DATE('30/11/1988', 'DD/MM/YYYY'), 'Third class actor');
INSERT INTO public.actor (firstname, lastname, birth_date, description) VALUES ('metka','kejzar', TO_DATE('30/11/1966', 'DD/MM/YYYY'), 'Theatre actor');
INSERT INTO public.actor (firstname, lastname, birth_date, description) VALUES ('miha','pogacar', TO_DATE('30/11/1977', 'DD/MM/YYYY'), 'First class actor');
INSERT INTO public.actor (firstname, lastname, birth_date, description) VALUES ('kristjan','zalaznik', TO_DATE('30/11/1996', 'DD/MM/YYYY'), 'Second class actor');

-- Movie data
INSERT INTO public.movie (title, description, release_year) VALUES ('Avatar','action movie', TO_DATE('30/11/2006', 'DD/MM/YYYY'));
INSERT INTO public.movie (title, description, release_year) VALUES ('Spiderman','action movie', TO_DATE('30/11/2003', 'DD/MM/YYYY'));
INSERT INTO public.movie (title, description, release_year) VALUES ('Pulp fiction','drama movie', TO_DATE('30/11/2008', 'DD/MM/YYYY'));
INSERT INTO public.movie (title, description, release_year) VALUES ('Carol','drama movie', TO_DATE('30/11/2009', 'DD/MM/YYYY'));
INSERT INTO public.movie (title, description, release_year) VALUES ('The Godfather','drama movie', TO_DATE('30/11/2011', 'DD/MM/YYYY'));
INSERT INTO public.movie (title, description, release_year) VALUES ('Shindlers list','drama movie', TO_DATE('30/11/2012', 'DD/MM/YYYY'));

-- movies involved data
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (1,1);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (1,2);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (1,3);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (1,4);

INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (2,2);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (2,3);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (2,4);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (2,5);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (2,6);

INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (3,1);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (3,3);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (3,4);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (3,5);

INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (4,2);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (4,3);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (4,5);

INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (5,5);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (5,6);

INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (6,6);
INSERT INTO public.movies_involved (actor_id, movie_imdb_id) VALUES (6,5);







