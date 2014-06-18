App = Ember.Application.create({
	LOG_TRANSITIONS: true
});

App.ApplicationAdapter = DS.FixtureAdapter.extend();

App.Router.map(function() {
  this.resource('incomes', function() {
    this.route('new');
    this.route('edit');
  });
  this.resource('expenses', function() {
    this.route('new');
    this.route('edit');
  });
  this.route("bills");
  this.route("loans");
  this.route("transfers");
  this.route("reports");
});

App.IndexRoute = Ember.Route.extend({
  model: function() {
	return Ember.RSVP.hash({
      total: this.store.find('total', 1),
      assets: this.store.find('asset'),
      loans: this.store.find('loans', 1),
      expenses: this.store.find('expense')
    });
  },
 setupController: function(controller, model) {
    this._super(controller,model);
	isEditing: true;
  }
});

App.IndexController = Ember.ObjectController.extend({
  actions: {
    addExpense: function () {
      this.set('isEditing', true);
    }
  },

  isEditing: true,
  names: ["Yehuda", "Tom"]
});


Ember.Handlebars.helper('format-date', function(date) {
  return moment(date).fromNow();
});

Ember.Handlebars.helper('format-currency', function(amount, symbol) {
  return accounting.formatMoney(amount / 100, symbol, 2, ".", ",", "%v %s");
});
