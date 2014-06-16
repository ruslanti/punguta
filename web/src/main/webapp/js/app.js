App = Ember.Application.create();

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
    return this.store.find('expense');
  }
});
