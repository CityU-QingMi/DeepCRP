	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof OneToOneEntity ) ) {
			return false;
		}

		OneToOneEntity that = (OneToOneEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( note != null ? !note.equals( that.note ) : that.note != null ) {
			return false;
		}

		return true;
	}
