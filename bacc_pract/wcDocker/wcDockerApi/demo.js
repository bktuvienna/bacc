/*
  Welcome to the Demo for the Web Cabin Docker!
*/
$(document).ready(function() {
  // --------------------------------------------------------------------------------
  // Create an instance of our docker window and assign it to the document.
  var myDocker = new wcDocker(document.body);
  if (myDocker) {

    var layoutConfiguration;
    var currentTheme = 'Default';

    // --------------------------------------------------------------------------------
    // Register the top panel, this is the static panel at the top of the window
    // that can not be moved or adjusted.  Note that this panel is also marked as
    // 'isPrivate', which means the user will not get the option to create more of these.
    myDocker.registerPanelType('Top Panel', {
      isPrivate: true,
      onCreate: function(myPanel) {
        // Add some text information into the panel
        myPanel.layout().addItem($('<div style="text-align:center"><strong>Welcome to the Web Cabin Docker!</strong><br>Web Cabin Docker is a docking panel layout interface written in JavaScript under the <a href="http://www.opensource.org/licenses/mit-license.php">MIT License</a>.</div>'), 0, 0);
        myPanel.layout().addItem($('<div style="text-align:center">View the source here: <a href="https://github.com/WebCabin/wcDocker">https://github.com/WebCabin/wcDocker</a></div>'), 0, 1);

        // Constrain the sizing of this window so the user can't resize it.
        myPanel.initSize(Infinity, 100);
        myPanel.minSize(100, 100);
        myPanel.maxSize(Infinity, 100);
        myPanel.title(false);

        // Do not allow the user to move or remove this panel, this will remove the title bar completely from the frame.
        myPanel.moveable(false);
        myPanel.closeable(false);
      }
    });

    // --------------------------------------------------------------------------------
    // Register our scrolling panel, a big empty panel with a large scrollable area inside.
    myDocker.registerPanelType('Scroll Panel', {
      faicon: 'unsorted',
      limit: 2,
      onCreate: function(myPanel) {
        // By adding an element into the layout that is much larger than the window, it will automatically be scrollable.
        // If you specifically do not want it to scroll, you can use the myPanel.scrollable function.
        myPanel.layout().addItem($('<div style="width:2000px;height:2000px;">'));
      }
    });

    // --------------------------------------------------------------------------------
    // Register our control panel, this contains various controls supported by the docker.
    myDocker.registerPanelType('Control Panel', {
      faicon: 'gears',
      onCreate: function(myPanel) {
        // Create a drop down with different themes to choose from.
        var $themeSelector = $('<div style="width:100%;text-align:center;" class="themeSelector">Theme: \
          <select>\
          <option value="Default">Default</option>\
          <option value="bigRed">Big Red</option>\
          <option value="shadow">Shadow</option>\
          </select></div>');
        // Initialize the current selection to the currently active theme.
        $themeSelector.find('select').val(currentTheme);

        // Create an element that will hold our custom context menu items.
        // The actual menu will be set up later, outside of the panel and be bound to the 'testMenu' class.
        var $testMenu = $('<div style="width:100%;height:20px;background-color:transparent;text-align:center;background-color:lightgray;" class="testMenu">Custom Context Menu here</div>');

        // Add Save and Restore buttons to record the current layout.
        var $saveButton = $('<button>Remember Layout</button>');
        var $restoreButton = $('<button class="restoreButton" style="float:right;">Restore Layout</button>');

        myPanel.layout().addItem($themeSelector, 0, 0, 2, 1);
        myPanel.layout().addItem($testMenu, 0, 1, 2, 1);
        myPanel.layout().addItem($saveButton, 0, 2);
        myPanel.layout().addItem($restoreButton, 1, 2);

        // Here we do some css table magic to make all other cells align to the top of the window.
        myPanel.layout().addItem($('<div>'), 0, 3, 2, 1).parent().css('height', '100%');

        // Bind an event to catch when the theme has been changed.
        $themeSelector.change(function() {
          currentTheme = $themeSelector.find('option:selected').val();

          // To load a theme, you just need to create a new 'link' element that includes the theme css file.
          // First we remove any already existing theme, so they don't conflict.
          $('#theme').remove();

          // The default theme requires no additional theme css file.
          if (currentTheme !== 'Default') {
            $('head').append($('<link id="theme" rel="stylesheet" type="text/css" href="Themes/' + currentTheme + '.css"/>'));
          }

          // In case there are multiple control panels, make sure every theme selector are updated with the new theme.
          $('.themeSelector select').each(function() {
            if ($(this)[0] !== $themeSelector.find('select')[0]) {
              $(this).val(currentTheme);
            }
          });
        });

        // layoutConfiguration is a global variable that stores the last saved layout data, if it is null then
        // disable the restore button, as there is no layout to restore.
        $restoreButton.attr('disabled', layoutConfiguration? false: true);

        // Setup a click handler for the save button.
        var saveTimer = 0;
        $saveButton.click(function() {
          // Save the layout into a global variable for later.
          layoutConfiguration = myDocker.save();

          // Enable all restore buttons on the page, as there may be more than one control panel open.
          $saveButton.html('<b>Remembered!</b>');
          $('.restoreButton').each(function() {
            $(this).attr('disabled', false);
          });

          // Notify the user that the layout is saved by changing the button text and restoring it after a time delay.
          if (saveTimer) {
            clearTimeout(saveTimer);
            saveTimer = 0;
          }
          saveTimer = setTimeout(function() {
            $saveButton.text('Remember Layout');
            saveTimer = 0;
          }, 500);
        });

        // Setup a click handler to restore a previously saved layout configuration.
        $restoreButton.click(function() {
          if (layoutConfiguration) {
            myDocker.restore(layoutConfiguration);
          }
        });
      }
    });

    // --------------------------------------------------------------------------------
    // Create a custom basic menu, found on the control panel.  Note, this does not happen inside
    // the creation of the panel, as it only needs to be created once and it will work on all
    // panels.  Here we add three custom menu options that appear on top of the normal context
    // options.  Use an empty object as a menu option to create a separator.
    myDocker.basicMenu('.testMenu', [
      {name: 'Custom Menu 1',                    callback: function(key, opts, panel){alert(key);}},
      {name: 'Custom Menu 2',                    callback: function(key, opts, panel){alert(key);}},
      {},
      {name: 'Custom Menu 3', faicon: 'refresh', callback: function(key, opts, panel){alert(key);}}
    ], true);

    // --------------------------------------------------------------------------------
    // Register the cell panel, a demonstration of the grid cell layout system.
    myDocker.registerPanelType('Panel Cells', {
      faicon: 'qrcode',
      onCreate: function(myPanel) {
        myPanel.layout().addItem($('<div style="text-align:center">All panels contain a gridded layout.</div>'), 0, 0, 1, 2);
        myPanel.layout().addItem($('<div style="text-align:center">This is a panel.</div>'), 1, 0);
        myPanel.layout().addItem($('<div style="text-align:center">Each grid cell can be used individually...</div>'), 1, 1);
        myPanel.layout().addItem($('<div style="text-align:center">or multiple cells can be merged.</div>'), 0, 2, 2, 1);

        // By default, the grid is hidden and there is no space in between each cell.  We also flag the grid to alternate row colors.
        myPanel.layout().showGrid(true);
        myPanel.layout().gridSpacing(5);
        myPanel.layout().gridAlternate(true);

        myPanel.initSize(400, 400);
      }
    });

    // --------------------------------------------------------------------------------
    // Register the creation panel, shows information on how to create new panels on the fly.
    myDocker.registerPanelType('Creation Panel', {
      faicon: 'star',
      onCreate: function(myPanel) {
        myPanel.layout().addItem($('<div style="text-align:center">New (and even duplicate) panels can be created by right clicking at the location you wish the new panel to go and using the context menu.</div>'));
        myPanel.layout().addItem($('<div>'), 0, 1);
      }
    });

    // --------------------------------------------------------------------------------
    // Register the Dock me panel, demonstrates floating panels and gives information
    // about docking and moving them.
    myDocker.registerPanelType('Dock Me', {
      faicon: 'crosshairs',
      onCreate: function(myPanel) {
        myPanel.layout().addItem($('<div style="text-align:center">Panels can float on their own, or be docked beside other panels.  Dragging the title bar will move all panels within the frame while dragging an individual tab item will separate the panel from its current frame.<br><br>Try moving this panel to a new location.</div>'));
        myPanel.initSize(400, 300);

        // Floating windows appear in the center of the screen by default, but that can be
        // changed by assigning a new init position.  Each parameter is a percentage value from 0-1.
        myPanel.initPos(0.5, 0.8);
      }
    });

    // --------------------------------------------------------------------------------
    // Register the Customized UI panel, demonstrates using the splitter window
    // and the custom tab area inside of a panel.
    myDocker.registerPanelType('Customized UI', {
      faicon: 'columns',
      onCreate: function(myPanel) {
        myPanel.initSize(400, 400);

        // We need at least one element in the main layout that can hold the splitter.  We give it classes wcWide and wcTall
        // to size it to the full size of the panel.
        var $scene = $('<div class="wcWide wcTall">')
        myPanel.layout().addItem($scene);

        // Here we can utilize the splitter used by wcDocker internally so that we may split up
        // a single panel.  Splitters can be nested, and new layouts can be created to fill
        // each side of the split.
        var splitter = new wcSplitter($scene, myPanel, wcDocker.ORIENTATION_HORIZONTAL);

        // Initialize this splitter with a layout in each pane.  This can be done manually, but
        // it is more convenient this way.
        splitter.initLayouts();

        // By default, the splitter splits down the middle, but the position can be assigned manually by giving it a percentage value from 0-1.
        splitter.pos(0.25);

        // Put some content in each layout.
        splitter.pane(0).addItem($('<div style="text-align:center">This panel is partitioned by it\'s own resizable splitter!</div>'));
        splitter.pane(1).addItem($('<div style="text-align:center">Each side of the splitter has it\'s own layout.<br><br>Toggle the rotation button in the upper right to change the orientation of the splitter.</div>'));

        var $tabArea = $('<div style="position:relative;left:10%;top:0px;width:80%;height:300px;border:1px solid black;"></div>');
        splitter.pane(1).addItem($tabArea, 0, 1);

        // Create our custom tab box and give it a containing element.
        var tabs = new wcTabFrame($tabArea, myPanel);
        tabs.addTab('Custom Tab 1').addItem($('<div style="text-align:center">This is a custom tab widget, designed to follow the current theme.  You can put this inside a containing element anywhere inside your panel.<br><br>Continue with the other tabs for more information...</div>'));
        tabs.addTab('Custom Tab 2').addItem($('<div style="text-align:center">Each tab has its own layout, and can be configured however you wish.</div>'));
        tabs.addTab('Custom Tab 3').addItem($('<div style="text-align:center">These tabs can "optionally" be re-orderable by the user, try to change the tab ordering by dragging them.</div>'));
        tabs.addTab('Custom Tab 4').addItem($('<div style="text-align:center">By default, tabs are not closeable, but we have enabled this one just for the sake of this demo.</div>'));
        tabs.addTab('Custom Tab 5').addItem($('<div style="text-align:center">Besides a tab being closeable, other options exist for each tab, whether they have a scrollable contents, or if elements can be visible outside of its boundaries, and more.</div>'));
        tabs.closeable(3, true);

        tabs.faicon(0, 'gears fa-spin')

        // We need to update the splitter whenever the panel is updated.
        myPanel.on(wcDocker.EVENT_UPDATED, function() {
          splitter.update();
          tabs.update();
        });

        // Add a rotation panel button to change the orientation of the splitter.
        myPanel.addButton('View', 'fa fa-rotate-right', 'O', 'Switch between horizontal and vertical layout.', true);
        myPanel.on(wcDocker.EVENT_BUTTON, function(data) {
          splitter.orientation(data.isToggled);
        });
      }
    });

    // --------------------------------------------------------------------------------
    // Register the reaction panel, demonstrates the event handler system.
    myDocker.registerPanelType('Reaction Panel', {
      faicon:'refresh',
      onCreate: function(myPanel) {
        // Setup a number of different text alerts that can be displayed based on certain events.
        var $buttonInfo = $('<div style="text-align:center">I react to the custom buttons above</div>');
        var $buttonN = $('<div style="text-align:center"><b>Happy button pressed!</b></div>');
        var $buttonTtrue = $('<div style="text-align:center"><b>Thumbs button is down!</b></div>');
        var $buttonTfalse = $('<div style="text-align:center"><b>Thumbs button is up!</b></div>');
        var buttonTimer;

        var $attachInfo = $('<div style="text-align:center">I react when docked</div>');
        var $attached   = $('<div style="text-align:center"><b>I was just docked!</b></div>');
        var $detachInfo = $('<div style="text-align:center">I react when detached</div>');
        var $detached   = $('<div style="text-align:center"><b>I was just detached!</b></div>');
        var attachTimer;

        var $moveInfo = $('<div style="text-align:center">I react on move</div>');
        var $moved    = $('<div style="text-align:center"><b>I was just moved!</b></div>');
        var moveTimer;

        var $resizeInfo = $('<div style="text-align:center">I react on resize</div>');
        var $resized    = $('<div style="text-align:center"><b>I was just resized!</b></div>');
        var resizeTimer;

        myPanel.layout().addItem($('<div style="text-align:center"></div>'));
        myPanel.layout().addItem($buttonInfo, 0, 0);
        myPanel.layout().addItem($detachInfo, 0, 1);
        myPanel.layout().addItem($moveInfo, 0, 2);
        myPanel.layout().addItem($resizeInfo, 0, 3);
        myPanel.layout().addItem($('<div style="text-align:center">Lastly, if you can see my tab icon, it will only be spinning when my panel is visible</div>'), 0, 4);

        // Add some custom buttons that will appear in the upper right corner of the panel.
        myPanel.addButton('Thumbs Button', 'fa fa-thumbs-up', 'T', 'A toggle button', true, 'fa fa-thumbs-down');
        myPanel.addButton('Happy Button', 'fa fa-smile-o', ':)', 'A normal button', false);

        // React on custom button press.
        myPanel.on(wcDocker.EVENT_BUTTON, function(data) {
          if (buttonTimer) {
            clearTimeout(buttonTimer);
          }

          if (data.name === 'Happy Button') {
            // Show an alert when the smile face button is clicked.
            this.layout().item(0, 0).empty();
            this.layout().addItem($buttonN, 0, 0);

            var self = this;
            buttonTimer = setTimeout(function() {
              self.layout().item(0, 0).empty();
              self.layout().addItem($buttonInfo, 0, 0);
              buttonTimer = 0;
            }, 1000);
          } else if (data.name === 'Thumbs Button') {
            // Show an alert when the thumbs button is toggled.
            this.layout().item(0, 0).empty();
            this.layout().addItem((data.isToggled? $buttonTtrue: $buttonTfalse), 0, 0)

            var self = this;
            buttonTimer = setTimeout(function() {
              self.layout().item(0, 0).empty();
              self.layout().addItem($buttonInfo, 0, 0);
              buttonTimer = 0;
            }, 1000);
          }
        });

        // React when this panel was floating and is now attached to a docking position.
        myPanel.on(wcDocker.EVENT_ATTACHED, function() {
          if (attachTimer) {
            clearTimeout(attachTimer);
          }

          this.layout().item(0, 1).empty();
          this.layout().addItem($attached, 0, 1);

          var self = this;
          attachTimer = setTimeout(function() {
            self.layout().item(0, 1).empty();
            self.layout().addItem($detachInfo, 0, 1);
            attachTimer = 0;
          }, 1000);
        });

        // React when this panel was docked and is now floating.
        myPanel.on(wcDocker.EVENT_DETACHED, function() {
          if (attachTimer) {
            clearTimeout(attachTimer);
          }

          this.layout().item(0, 1).empty();
          this.layout().addItem($detached, 0, 1);

          var self = this;
          attachTimer = setTimeout(function() {
            self.layout().item(0, 1).empty();
            self.layout().addItem($attachInfo, 0, 1);
            attachTimer = 0;
          }, 1000);
        });

        // React when this panel's top left position has changed.
        myPanel.on(wcDocker.EVENT_MOVED, function() {
          if (moveTimer) {
            clearTimeout(moveTimer);
          }

          this.layout().item(0, 2).empty();
          this.layout().addItem($moved, 0, 2);

          var self = this;
          moveTimer = setTimeout(function() {
            self.layout().item(0, 2).empty();
            self.layout().addItem($moveInfo, 0, 2);
            moveTimer = 0;
          }, 500);
        });

        // React on resizing.
        myPanel.on(wcDocker.EVENT_RESIZED, function() {
          if (resizeTimer) {
            clearTimeout(resizeTimer);
          }

          this.layout().item(0, 3).empty();
          this.layout().addItem($resized, 0, 3);

          var self = this;
          resizeTimer = setTimeout(function() {
            self.layout().item(0, 3).empty();
            self.layout().addItem($resizeInfo, 0, 3);
            resizeTimer = 0;
          }, 500);
        });

        myPanel.on(wcDocker.EVENT_VISIBILITY_CHANGED, function() {
          if (this.isVisible()) {
            this.faicon('refresh fa-spin');
          } else {
            this.faicon('refresh');
          }
        });
      }
    });

    // --------------------------------------------------------------------------------
    // Register the memory panel, demonstrates how internal data can be remembered along with the layout.
    myDocker.registerPanelType('Memory Panel', {
      faicon:'save',
      onCreate: function(myPanel) {
        // Create a number input control for the user to set.
        var $spinner = $('<input type="number" value="0"/>');
        var $info = $('<div style="text-align:center">I will remember this number when the layout is remembered:</div>');
        $info.append($spinner);

        myPanel.layout().addItem($info);

        // Setup an event handler for when the layout is being saved.
        myPanel.on(wcDocker.EVENT_SAVE_LAYOUT, function(data) {
          // The data parameter given is an empty object, fill it with whatever you want to record.
          data.value = $spinner.val();
        });

        // Setup an event handler for when the layout is being restored.
        myPanel.on(wcDocker.EVENT_RESTORE_LAYOUT, function(data) {
          // The data parameter should be filled with any previously saved data, here we restore our saved number value.
          $spinner.val(data.value);
        });
      }
    });

    // --------------------------------------------------------------------------------
    // Register a modal dialog panel as an introduction.
    myDocker.registerPanelType('Introduction', {
      faicon: 'exclamation',
      onCreate: function(myPanel) {
        myPanel.layout().addItem($('<div style="text-align:center;margin:20px;">Welcome to the Web Cabin Docker!<br><br>This demonstration has been made to show you some of the features available to you when using wcDocker.</div>'));
        myPanel.layout().addItem($('<div style="text-align:center;margin:20px;">The first example is this panel.  A modal panel that blocks access to other panels until it has been closed.</div>'), 0, 1);

        var $button = $('<button type="button" style="float:right;width:150px;margin:20px;">Continue...</button>');
        var $buttonContainer = $('<div>');

        $buttonContainer.append($button);
        myPanel.layout().addItem($buttonContainer, 0, 2).parent().css('vertical-align', 'bottom');

        myPanel.initSize(500, 500);

        $button.click(function() {
          myPanel.close();
        });
      },
      isPrivate: true,
    });

    // --------------------------------------------------------------------------------
    // Here we actually add all of our registered panels into our document.
    // The order that each panel is added makes a difference.  In general, start
    // by creating the center panel and work your way outwards in all directions.
    var panel1 = myDocker.addPanel('Control Panel', wcDocker.DOCK_BOTTOM, false);

    var panel2 = myDocker.addPanel('Reaction Panel', wcDocker.DOCK_TOP, false, panel1);
    var panel3 = myDocker.addPanel('Creation Panel', wcDocker.DOCK_RIGHT, false, panel2);
    myDocker.addPanel('Reaction Panel', wcDocker.DOCK_BOTTOM, true, panel3);
    myDocker.addPanel('Control Panel', wcDocker.DOCK_BOTTOM, true, panel3);
    myDocker.addPanel('Panel Cells', wcDocker.DOCK_BOTTOM, true, panel3);
    myDocker.addPanel('Scroll Panel', wcDocker.DOCK_BOTTOM, true, panel3);
    myDocker.addPanel('Memory Panel', wcDocker.DOCK_BOTTOM, true, panel3);
    myDocker.addPanel('Customized UI', wcDocker.DOCK_BOTTOM, true, panel3);
    myDocker.addPanel('Dock Me', wcDocker.DOCK_BOTTOM, true, panel3);

    var panel4 = myDocker.addPanel('Panel Cells', wcDocker.DOCK_LEFT, false);
    myDocker.addPanel('Scroll Panel', wcDocker.DOCK_BOTTOM, true, panel4);
    myDocker.addPanel('Memory Panel', wcDocker.DOCK_BOTTOM, false, panel4);

    myDocker.addPanel('Customized UI', wcDocker.DOCK_RIGHT, false);

    myDocker.addPanel('Top Panel', wcDocker.DOCK_TOP, false);

    myDocker.addPanel('Dock Me', wcDocker.DOCK_FLOAT, false);

    myDocker.addPanel('Introduction', wcDocker.DOCK_MODAL, false);
  }
});