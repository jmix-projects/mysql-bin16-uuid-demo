package com.company.demo.screen.foo;

import com.company.demo.entity.Foo;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("Foo.browse")
@UiDescriptor("foo-browse.xml")
@LookupComponent("foosTable")
public class FooBrowse extends StandardLookup<Foo> {
}