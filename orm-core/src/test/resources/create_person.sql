CREATE TABLE public.person
(
  per_id serial NOT NULL,
  name character varying NOT NULL,
  lastname character varying NOT NULL,
  age integer NOT NULL,
  birth_date date NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.person
  OWNER TO postgres;

