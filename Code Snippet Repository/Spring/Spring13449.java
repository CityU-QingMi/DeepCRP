	@Test
	public void collectionOfPetsAsStringNotSelected() throws Exception {
		this.tag.setPath("pets");
		this.tag.setValue("Santa's Little Helper");

		int result = this.tag.doStartTag();
		assertEquals(Tag.SKIP_BODY, result);

		String output = getOutput();

		// wrap the output so it is valid XML
		output = "<doc>" + output + "</doc>";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new StringReader(output));
		Element checkboxElement = (Element) document.getRootElement().elements().get(0);
		assertEquals("input", checkboxElement.getName());
		assertEquals("checkbox", checkboxElement.attribute("type").getValue());
		assertEquals("pets", checkboxElement.attribute("name").getValue());
		assertNull(checkboxElement.attribute("checked"));
	}
