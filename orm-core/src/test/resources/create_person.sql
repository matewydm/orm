CREATE TABLE public.person
(
  per_id integer NOT NULL DEFAULT nextval('person_per_id_seq'::regclass),
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

