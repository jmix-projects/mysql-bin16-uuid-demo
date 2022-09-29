package com.company.demo.screen.foo;

import com.company.demo.entity.Foo;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("Foo.edit")
@UiDescriptor("foo-edit.xml")
@EditedEntityContainer("fooDc")
public class FooEdit extends StandardEditor<Foo> {
}