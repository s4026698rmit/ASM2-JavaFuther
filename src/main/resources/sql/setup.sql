-- WARNING: This schema is for context only and is not meant to be run.
-- Table order and constraints may not be valid for execution.

CREATE TABLE public.attendees (
  person_id bigint NOT NULL,
  notes text,
  CONSTRAINT attendees_pkey PRIMARY KEY (person_id),
  CONSTRAINT attendees_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.persons(id)
);
CREATE TABLE public.audit_logs (
  id bigint NOT NULL DEFAULT nextval('audit_logs_id_seq'::regclass),
  actor_person_id bigint,
  action_type character varying NOT NULL,
  entity_type character varying NOT NULL,
  entity_id bigint,
  details jsonb,
  created_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT audit_logs_pkey PRIMARY KEY (id),
  CONSTRAINT audit_logs_actor_person_id_fkey FOREIGN KEY (actor_person_id) REFERENCES public.persons(id)
);
CREATE TABLE public.event_admins (
  person_id bigint NOT NULL,
  CONSTRAINT event_admins_pkey PRIMARY KEY (person_id),
  CONSTRAINT event_admins_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.persons(id)
);
CREATE TABLE public.events (
  id bigint NOT NULL DEFAULT nextval('events_id_seq'::regclass),
  name character varying NOT NULL,
  type USER-DEFINED NOT NULL,
  description text,
  start_date date NOT NULL,
  end_date date NOT NULL,
  location character varying NOT NULL,
  status USER-DEFINED NOT NULL DEFAULT 'SCHEDULED'::event_status,
  capacity integer CHECK (capacity IS NULL OR capacity >= 0),
  image_data bytea,
  created_at timestamp with time zone NOT NULL DEFAULT now(),
  updated_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT events_pkey PRIMARY KEY (id)
);
CREATE TABLE public.persons (
  id bigint NOT NULL DEFAULT nextval('persons_id_seq'::regclass),
  full_name character varying NOT NULL,
  date_of_birth date,
  email character varying NOT NULL UNIQUE,
  phone character varying,
  username character varying NOT NULL UNIQUE,
  password_hash text NOT NULL,
  role USER-DEFINED NOT NULL,
  created_at timestamp with time zone NOT NULL DEFAULT now(),
  updated_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT persons_pkey PRIMARY KEY (id)
);
CREATE TABLE public.presenters (
  person_id bigint NOT NULL,
  bio text,
  role_title character varying,
  CONSTRAINT presenters_pkey PRIMARY KEY (person_id),
  CONSTRAINT presenters_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.persons(id)
);
CREATE TABLE public.schedules (
  id bigint NOT NULL DEFAULT nextval('schedules_id_seq'::regclass),
  attendee_id bigint NOT NULL,
  session_id bigint NOT NULL,
  created_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT schedules_pkey PRIMARY KEY (id),
  CONSTRAINT schedules_attendee_id_fkey FOREIGN KEY (attendee_id) REFERENCES public.attendees(person_id),
  CONSTRAINT schedules_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id)
);
CREATE TABLE public.session_materials (
  id bigint NOT NULL DEFAULT nextval('session_materials_id_seq'::regclass),
  session_id bigint NOT NULL,
  uploaded_by bigint NOT NULL,
  file_name character varying NOT NULL,
  mime_type character varying NOT NULL,
  file_data bytea NOT NULL,
  uploaded_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT session_materials_pkey PRIMARY KEY (id),
  CONSTRAINT session_materials_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id),
  CONSTRAINT session_materials_uploaded_by_fkey FOREIGN KEY (uploaded_by) REFERENCES public.presenters(person_id)
);
CREATE TABLE public.session_presenters (
  session_id bigint NOT NULL,
  presenter_id bigint NOT NULL,
  CONSTRAINT session_presenters_pkey PRIMARY KEY (session_id, presenter_id),
  CONSTRAINT session_presenters_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id),
  CONSTRAINT session_presenters_presenter_id_fkey FOREIGN KEY (presenter_id) REFERENCES public.presenters(person_id)
);
CREATE TABLE public.sessions (
  id bigint NOT NULL DEFAULT nextval('sessions_id_seq'::regclass),
  event_id bigint NOT NULL,
  title character varying NOT NULL,
  description text,
  start_time timestamp with time zone NOT NULL,
  end_time timestamp with time zone NOT NULL,
  venue character varying NOT NULL,
  capacity integer NOT NULL CHECK (capacity >= 0),
  status USER-DEFINED NOT NULL DEFAULT 'SCHEDULED'::session_status,
  materials_note text,
  created_at timestamp with time zone NOT NULL DEFAULT now(),
  updated_at timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT sessions_pkey PRIMARY KEY (id),
  CONSTRAINT sessions_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.events(id)
);
CREATE TABLE public.system_admins (
  person_id bigint NOT NULL,
  CONSTRAINT system_admins_pkey PRIMARY KEY (person_id),
  CONSTRAINT system_admins_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.persons(id)
);
CREATE TABLE public.tickets (
  id bigint NOT NULL DEFAULT nextval('tickets_id_seq'::regclass),
  ticket_code character varying NOT NULL UNIQUE,
  ticket_type USER-DEFINED NOT NULL DEFAULT 'STANDARD'::ticket_type,
  price numeric NOT NULL DEFAULT 0,
  status USER-DEFINED NOT NULL DEFAULT 'ACTIVE'::ticket_status,
  attendee_id bigint NOT NULL,
  session_id bigint NOT NULL,
  qr_code_data bytea NOT NULL,
  issued_at timestamp with time zone NOT NULL DEFAULT now(),
  used_at timestamp with time zone,
  cancelled_at timestamp with time zone,
  CONSTRAINT tickets_pkey PRIMARY KEY (id),
  CONSTRAINT tickets_attendee_id_fkey FOREIGN KEY (attendee_id) REFERENCES public.attendees(person_id),
  CONSTRAINT tickets_session_id_fkey FOREIGN KEY (session_id) REFERENCES public.sessions(id)
);