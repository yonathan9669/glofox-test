SET check_function_bodies = false;
CREATE FUNCTION public.set_current_timestamp_updated_at() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
  _new record;
BEGIN
  _new := NEW;
  _new."updated_at" = NOW();
  RETURN _new;
END;
$$;
CREATE TABLE public.activity (
    id integer NOT NULL,
    event_id integer NOT NULL,
    name text NOT NULL,
    capacity integer NOT NULL,
    description text,
    date_range daterange NOT NULL,
    start_at time with time zone NOT NULL,
    end_at time with time zone NOT NULL,
    responsible_id integer,
    type text NOT NULL,
    created_at timestamp with time zone DEFAULT now(),
    updated_at timestamp with time zone DEFAULT now()
);
CREATE SEQUENCE public.activity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.activity_id_seq OWNED BY public.activity.id;
CREATE TABLE public.activity_type (
    name text NOT NULL
);
CREATE TABLE public.booking (
    id integer NOT NULL,
    member_id integer NOT NULL,
    activity_id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    attended boolean,
    created_at timestamp with time zone DEFAULT now(),
    updated_at timestamp with time zone DEFAULT now()
);
CREATE SEQUENCE public.booking_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.booking_id_seq OWNED BY public.booking.id;
CREATE TABLE public.business (
    id integer NOT NULL,
    name text NOT NULL,
    address text,
    owner_id integer NOT NULL,
    type text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);
CREATE SEQUENCE public.business_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.business_id_seq OWNED BY public.business.id;
CREATE TABLE public.business_type (
    name text NOT NULL
);
CREATE TABLE public.event (
    id integer NOT NULL,
    business_id integer NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    type text NOT NULL,
    created_at timestamp with time zone DEFAULT now(),
    updated_at timestamp with time zone DEFAULT now()
);
CREATE SEQUENCE public.event_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.event_id_seq OWNED BY public.event.id;
CREATE TABLE public.event_type (
    name text NOT NULL
);
CREATE TABLE public.membership (
    id integer NOT NULL,
    member_id integer NOT NULL,
    business_id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);
CREATE SEQUENCE public.membership_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.membership_id_seq OWNED BY public.membership.id;
CREATE TABLE public."user" (
    id integer NOT NULL,
    name text NOT NULL,
    email text,
    phone text,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL
);
CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;
ALTER TABLE ONLY public.activity ALTER COLUMN id SET DEFAULT nextval('public.activity_id_seq'::regclass);
ALTER TABLE ONLY public.booking ALTER COLUMN id SET DEFAULT nextval('public.booking_id_seq'::regclass);
ALTER TABLE ONLY public.business ALTER COLUMN id SET DEFAULT nextval('public.business_id_seq'::regclass);
ALTER TABLE ONLY public.event ALTER COLUMN id SET DEFAULT nextval('public.event_id_seq'::regclass);
ALTER TABLE ONLY public.membership ALTER COLUMN id SET DEFAULT nextval('public.membership_id_seq'::regclass);
ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);
ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.business
    ADD CONSTRAINT business_name_key UNIQUE (name);
ALTER TABLE ONLY public.business
    ADD CONSTRAINT business_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.business_type
    ADD CONSTRAINT business_type_pkey PRIMARY KEY (name);
ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.activity_type
    ADD CONSTRAINT event_type_pkey PRIMARY KEY (name);
ALTER TABLE ONLY public.event_type
    ADD CONSTRAINT event_type_pkey1 PRIMARY KEY (name);
ALTER TABLE ONLY public.membership
    ADD CONSTRAINT membership_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_email_key UNIQUE (email);
ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
CREATE TRIGGER set_public_activity_updated_at BEFORE UPDATE ON public.activity FOR EACH ROW EXECUTE FUNCTION public.set_current_timestamp_updated_at();
COMMENT ON TRIGGER set_public_activity_updated_at ON public.activity IS 'trigger to set value of column "updated_at" to current timestamp on row update';
CREATE TRIGGER set_public_booking_updated_at BEFORE UPDATE ON public.booking FOR EACH ROW EXECUTE FUNCTION public.set_current_timestamp_updated_at();
COMMENT ON TRIGGER set_public_booking_updated_at ON public.booking IS 'trigger to set value of column "updated_at" to current timestamp on row update';
CREATE TRIGGER set_public_business_updated_at BEFORE UPDATE ON public.business FOR EACH ROW EXECUTE FUNCTION public.set_current_timestamp_updated_at();
COMMENT ON TRIGGER set_public_business_updated_at ON public.business IS 'trigger to set value of column "updated_at" to current timestamp on row update';
CREATE TRIGGER set_public_event_updated_at BEFORE UPDATE ON public.event FOR EACH ROW EXECUTE FUNCTION public.set_current_timestamp_updated_at();
COMMENT ON TRIGGER set_public_event_updated_at ON public.event IS 'trigger to set value of column "updated_at" to current timestamp on row update';
CREATE TRIGGER set_public_membership_updated_at BEFORE UPDATE ON public.membership FOR EACH ROW EXECUTE FUNCTION public.set_current_timestamp_updated_at();
COMMENT ON TRIGGER set_public_membership_updated_at ON public.membership IS 'trigger to set value of column "updated_at" to current timestamp on row update';
CREATE TRIGGER set_public_user_updated_at BEFORE UPDATE ON public."user" FOR EACH ROW EXECUTE FUNCTION public.set_current_timestamp_updated_at();
COMMENT ON TRIGGER set_public_user_updated_at ON public."user" IS 'trigger to set value of column "updated_at" to current timestamp on row update';
ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.event(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_responsible_id_fkey FOREIGN KEY (responsible_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_type_fkey FOREIGN KEY (type) REFERENCES public.activity_type(name) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_activity_id_fkey FOREIGN KEY (activity_id) REFERENCES public.activity(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_member_id_fkey FOREIGN KEY (member_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.business
    ADD CONSTRAINT business_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.business
    ADD CONSTRAINT business_type_fkey FOREIGN KEY (type) REFERENCES public.business_type(name) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_business_id_fkey FOREIGN KEY (business_id) REFERENCES public.business(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_type_fkey FOREIGN KEY (type) REFERENCES public.event_type(name) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.membership
    ADD CONSTRAINT membership_business_id_fkey FOREIGN KEY (business_id) REFERENCES public.business(id) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE ONLY public.membership
    ADD CONSTRAINT membership_member_id_fkey FOREIGN KEY (member_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE RESTRICT;
