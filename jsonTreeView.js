// GetChildren and GetTreeItem are required as vscode api uses this to render the tree view

const vscode = require('vscode');

class JsonTreeView {
  constructor(context) {
    this._treeDataProvider = new JsonTreeDataProvider();
    this.title = "jsonTreeView"
    this._treeView = vscode.window.createTreeView(
        'jsonTreeView', 
        { treeDataProvider: this._treeDataProvider }
    );
    context.subscriptions.push(
        vscode.commands.registerCommand('extension.treeItemClicked', (item) => {
              
            if (typeof item.value !== 'object'){
                vscode.window.showInformationMessage(`TreeItem ${item.label} has value ${item.value}`);
            }

        })
        );
  
    }

  refresh(data) {
    this._treeDataProvider.refresh(data);
  }
}

class JsonTreeDataProvider {
  constructor() {
    this._onDidChangeTreeData = new vscode.EventEmitter();
    this.onDidChangeTreeData = this._onDidChangeTreeData.event;
    this.data = null;
  }

  refresh(data) {
    this.data = data;
    this._onDidChangeTreeData.fire();
  }

  getTreeItem(element) {
    const treeItem = new vscode.TreeItem(
        element.label,
        typeof element.value === 'object' 
        ? vscode.TreeItemCollapsibleState.Collapsed 
        : vscode.TreeItemCollapsibleState.None
      );
  
      treeItem.command = {
        command: 'extension.treeItemClicked',
        title: 'Tree Item Clicked',
        arguments: [element]
      };
  
      treeItem.tooltip = JSON.stringify(element.value);
      treeItem.description = typeof element.value !== 'object' ? String(element.value) : '';
  
      return treeItem;
    }

  getChildren(element) {
    if (!this.data) {
      return Promise.resolve([]);
    }

    if (element) {
      return Promise.resolve(this.getChildrenElements(element.value));
    } else {
      return Promise.resolve(this.getChildrenElements(this.data));
    }
  }

  getChildrenElements(data) {
    if (typeof data === 'object') {
        return Object.keys(data).map(
            key => new JsonTreeItem(key, data[key])
        );
    }
    return [];
  }
}

class JsonTreeItem extends vscode.TreeItem {
  constructor(label, value) {
    super(label, typeof value === 'object' 
        ? vscode.TreeItemCollapsibleState.Collapsed 
        : vscode.TreeItemCollapsibleState.None);
    this.value = value;

    if (typeof value !== 'object') {
      this.description = String(value);
    }
  }
}

module.exports = JsonTreeView;
